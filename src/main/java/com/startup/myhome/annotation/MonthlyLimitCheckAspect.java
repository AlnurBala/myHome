package com.startup.myhome.annotation;

import com.startup.myhome.dto.response.UsersResponseDto;
import com.startup.myhome.exception.MonthlyLimitExceededException;
import com.startup.myhome.service.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MonthlyLimitCheckAspect {
    private static final Logger logger
            = LoggerFactory.getLogger(MonthlyLimitCheckAspect.class);

    private final UsersServiceImpl usersService;

    @Before("@annotation(checkMonthlyLimit)")
    public void checkMonthlyLimit(CheckMonthlyLimit checkMonthlyLimit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isUser(authentication)) {
            String userEmail = authentication.getName();
            UsersResponseDto user = usersService.getUserByEmail(userEmail);

            int currentMonthlyLimit = user.getCurrentMonthlyLimit();

            if (currentMonthlyLimit <= 0) {
                logger.error("ERROR: Monthly limit exceeded for user: {}", userEmail);
                throw new MonthlyLimitExceededException("Monthly limit exceeded for user: " + userEmail);
            }

            // Decrease the limit after a successful request
            currentMonthlyLimit--;

            // Update the currentMonthlyLimit in the database
            user.setCurrentMonthlyLimit(currentMonthlyLimit);
            usersService.updateUser(user.getUserId(), user);

            logger.info("INFO: The current monthly limit for user {} is {}", user.getEmail(), user.getCurrentMonthlyLimit());
        }
    }

    private boolean isUser(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));
    }
}

package com.startup.myhome.service.impl;

import com.startup.myhome.dto.request.UsersRequest;
import com.startup.myhome.dto.response.UsersResponseDto;
import com.startup.myhome.entity.Users;
import com.startup.myhome.exception.NotDataFoundException;
import com.startup.myhome.mapper.UsersMapper;
import com.startup.myhome.repository.UsersRepository;
import com.startup.myhome.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UsersRepository userRepository;
    private final UsersMapper userMapper;

    @Override
    public List<UsersResponseDto> getAllUsers() {
        log.debug("Fetching all users");
        var userEntity = userRepository.findAll();
        return userMapper.toDTOs(userEntity);
    }

    public UsersResponseDto getUserByEmail(String email) {
        log.debug("Find user with email: {}", email);
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            log.error("User with the email {} not found", email);
            return null;
        }
        Users user = userOptional.get();
        return userMapper.toDTO(user);
    }

    @Override
    public UsersResponseDto createUser(UsersRequest userRequest) {
        log.info("Creating user: {}", userRequest);
//        if (userRequest.getRole() == null) {
//            userRequest.setRole("USER");
//        }
        var userEntity = userMapper.fromDTO(userRequest);
        userEntity = userRepository.save(userEntity);
        log.info("User created: {}", userEntity);
        return userMapper.toDTO(userEntity);
    }

    @Override
    public UsersResponseDto updateUser(Integer id, UsersRequest userRequest) {
        log.debug("Updating user with ID {}: {}", id, userRequest);
        Optional<Users> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            log.error("User with ID {} not found for update", id);
            return null;
        }
        Users newUser = userOptional.get();
        userMapper.mapUpdateRequestToEntity(newUser, userRequest);
        newUser = userRepository.save(newUser);
        log.info("User updated: {}", newUser);
        return userMapper.toDTO(newUser);
    }

    @Override
    public void deleteUsers(Integer id) {
        log.warn("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("User deleted with ID: {}", id);
    }

    public void updateUser(Integer id, UsersResponseDto usersResponseDto) {
        log.debug("DEBUG: Updating user with ID {}: {}", id, usersResponseDto);
        Users users = userRepository.findById(id)
                .orElseGet(() -> {
                    log.error("User with ID {} not found for update", id);
                    throw new NotDataFoundException("User with ID not found for update");
                });

        userMapper.mapUpdateResponseToEntity(users, usersResponseDto);
        userRepository.save(users);
    }
}

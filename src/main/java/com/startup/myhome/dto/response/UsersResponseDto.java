package com.startup.myhome.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponseDto {
    private Integer userId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer monthlyLimit;
    private Integer currentMonthlyLimit;
    private String role;
}

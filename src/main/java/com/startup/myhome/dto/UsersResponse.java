package com.startup.myhome.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponse {
    private Integer userId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer monthlyLimit;
    private String role;


}

package com.startup.myhome.service;

import com.startup.myhome.dto.request.UsersRequest;
import com.startup.myhome.dto.response.UsersResponseDto;

import java.util.List;

public interface UsersService {
    List<UsersResponseDto> getAllUsers();

    UsersResponseDto createUser(UsersRequest usersRequest);

    UsersResponseDto updateUser(Integer id, UsersRequest userRequest);

    void deleteUsers(Integer id);

}

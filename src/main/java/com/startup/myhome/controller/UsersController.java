package com.startup.myhome.controller;

import com.startup.myhome.dto.request.UsersRequest;
import com.startup.myhome.dto.response.UsersResponseDto;
import com.startup.myhome.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public List<UsersResponseDto> getAllUsers() {

        return usersService.getAllUsers();
    }

    @PostMapping
    public UsersResponseDto createUser(@RequestBody @Valid UsersRequest usersRequest) {
        return usersService.createUser(usersRequest);
    }

    @PutMapping("/{id}")
    public UsersResponseDto updateUser(@PathVariable Integer id, @RequestBody UsersRequest usersRequest) {
        return usersService.updateUser(id, usersRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        usersService.deleteUsers(id);
    }

}

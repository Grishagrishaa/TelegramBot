package com.example.userservice.controllers.api;

import com.example.userservice.dto.users.UserCreateDto;
import com.example.userservice.dto.users.UserReadAndUpdateDto;
import org.springframework.http.ResponseEntity;

public interface IUserController {

    ResponseEntity<UserReadAndUpdateDto> getUser(Long userId);

    void createUser(UserCreateDto user);

    void updateUser(UserReadAndUpdateDto dto);
}

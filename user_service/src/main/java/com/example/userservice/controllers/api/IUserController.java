package com.example.userservice.controllers.api;

import com.example.userservice.dto.users.UserCreateDto;
import com.example.userservice.dto.users.UserReadAndUpdateDto;
import org.springframework.http.ResponseEntity;

public interface IUserController {

    /**
     *
     * @param userId User identifier
     * @return ResponseEnity -> Status + Body(UserReadAndUpdateDto)
     */
    ResponseEntity<UserReadAndUpdateDto> getUser(Long userId);

    /**
     * Persist User into the Database
     * @param user DTO for creating User
     */
    void createUser(UserCreateDto user);

    /**
     * Update Existing User in the Database
     * @param dto DTO for read and update
     */
    void updateUser(UserReadAndUpdateDto dto);
}

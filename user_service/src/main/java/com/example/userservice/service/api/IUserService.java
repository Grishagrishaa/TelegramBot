package com.example.userservice.service.api;

import com.example.userservice.dao.entity.User;
import com.example.userservice.dto.users.UserCreateDto;
import com.example.userservice.dto.users.UserReadAndUpdateDto;

public interface IUserService {
    /**
     * Load user from database by UserId
     * @param userId  User identifier
     * @return User
     */
    User getUser(Long userId);

    /**
     *Persist user into database
     * @param userDto  DTO for creating User.
     */
    void saveUser(UserCreateDto userDto);

    /**
     * Update Existing User
     * @param dto for read and update
     */
    void updateUser(UserReadAndUpdateDto dto);

}

package com.example.userservice.service.api;

import com.example.userservice.dao.entity.User;
import com.example.userservice.dto.users.UserCreateDto;
import com.example.userservice.dto.users.UserReadAndUpdateDto;

public interface IUserService {
    User getUser(Long chatId);

    void saveUser(UserCreateDto user);

    void updateUser(UserReadAndUpdateDto dto);

}

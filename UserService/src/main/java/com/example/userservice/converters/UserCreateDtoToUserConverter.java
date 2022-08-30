package com.example.userservice.converters;

import com.example.userservice.dao.entity.User;
import com.example.userservice.dto.users.UserCreateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCreateDtoToUserConverter implements Converter<UserCreateDto, User> {
    @Override
    public User convert(UserCreateDto dto) {
        return User.Builder.create()
                .setUserId(dto.getUserId())
                .setCreatedDate(null)
                .setUpdatedDate(null)
                .setCity(null)
                .setPageSize(null)
                .build();
    }
}

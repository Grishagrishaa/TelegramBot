package com.example.userservice.converters;

import com.example.userservice.dao.entity.User;
import com.example.userservice.dto.users.UserReadAndUpdateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserReadDtoConverter implements Converter<User, UserReadAndUpdateDto> {

    @Override
    public UserReadAndUpdateDto convert(User user) {
        return UserReadAndUpdateDto.Builder.create()
                .setUserId(user.getUserId())
                .setCity(user.getCity())
                .setPageSize(user.getPageSize())
                .build();
    }
}

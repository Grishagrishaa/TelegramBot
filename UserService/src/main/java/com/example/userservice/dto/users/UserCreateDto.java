package com.example.userservice.dto.users;

public class UserCreateDto {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserCreateDto{" +
                "chatId=" + userId +
                '}';
    }
}

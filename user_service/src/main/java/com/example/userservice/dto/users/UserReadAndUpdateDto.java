package com.example.userservice.dto.users;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UserReadAndUpdateDto.Builder.class)
public class UserReadAndUpdateDto {
    private final Long userId;
    private final String city;
    private final Integer pageSize;

    private UserReadAndUpdateDto(Long userId, String city, Integer pageSize) {
        this.userId = userId;
        this.city = city;
        this.pageSize = pageSize;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCity() {
        return city;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        private Long userId;
        private String city;
        private Integer pageSize;

        private Builder() {
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public static Builder create(){
            return new Builder();
        }

        public UserReadAndUpdateDto build(){
            return new UserReadAndUpdateDto(userId, city, pageSize);
        }
    }

    @Override
    public String toString() {
        return "UserReadDto{" +
                "chatId=" + userId +
                ", city='" + city + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}

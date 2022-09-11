package com.example.userservice.dto.users;

public class UserCreateDto {
    private Long userId;
    private String city;
    private Integer pageSize;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "UserCreateDto{" +
                "userId=" + userId +
                ", city='" + city + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}

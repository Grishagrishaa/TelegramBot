package org.example.telegramBot.service.impl;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {
    @JsonProperty("user_id")
    private Long userId;
    private String city;
    @JsonProperty("page_size")
    private Integer pageSize;

    public UserData() {
    }

    public UserData(Long userId) {
        this.userId = userId;
    }

    public static UserData getDefaults(Long userId){
        UserData userData = new UserData(userId);

        userData.setCity(null);
        userData.setPageSize(5);
        return userData;
    }

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
}

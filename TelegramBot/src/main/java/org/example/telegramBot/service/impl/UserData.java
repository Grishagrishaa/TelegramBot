package org.example.telegramBot.service.impl;


public class UserData {
    private Long userId;
    private String city;
    private Integer pageSize;

    public UserData(Long userId) {
        this.userId = userId;
    }

    public static UserData getDefaults(){
        UserData userData = new UserData(9999999999L);
        userData.setCity(null);
        userData.setPageSize(null);
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

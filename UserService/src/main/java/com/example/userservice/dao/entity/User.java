package com.example.userservice.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = "users", name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonDeserialize(builder = User.Builder.class)
public class User {
    @Id
    private Long userId;
    @CreatedDate
    private LocalDateTime createdDate;
    @Version
    private LocalDateTime updatedDate;
    private String city;
    private Integer pageSize;

    private User(Builder builder) {
        this.userId = builder.userId;
        this.createdDate = builder.createdDate;
        this.updatedDate = builder.updatedDate;
        this.city = builder.city;
        this.pageSize = builder.pageSize;
    }

    public User() {
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public String getCity() {
        return city;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        @Id
        private Long userId;
        @CreatedDate
        private LocalDateTime createdDate;
        @Version
        private LocalDateTime updatedDate;
        private String city;
        private Integer pageSize;

        private Builder() {
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setUpdatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
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

        public User build(){
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + userId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", city='" + city + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}

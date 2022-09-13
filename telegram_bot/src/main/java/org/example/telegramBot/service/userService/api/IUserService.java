package org.example.telegramBot.service.userService.api;

import org.example.telegramBot.service.impl.userService.UserData;
import org.springframework.web.reactive.function.client.WebClientException;

public interface IUserService {
    /**
     *
     * @param userId - unique ID
     * @return userData from user_service
     * @throws WebClientException - In case, when user_service response isn't 2xx - wasn't found
     */
    UserData getUserDataByUserId(Long userId);

    /**
     * Update user data in user_service, sending new data to user_service
     * @param userData Information about user (UserId, City, PageSize)
     * @throws WebClientException In case, when user_service response isn't 2xx - user wasn't updated
     */
    void updateUser(UserData userData);

    /**
     * Save user data in user_service, sending data to user_service
     * @param userData Information about user (UserId, City, PageSize)
     * @throws WebClientException In case, when user_service response isn't 2xx - user wasn't saved
     */
    void createUser(UserData userData);
}

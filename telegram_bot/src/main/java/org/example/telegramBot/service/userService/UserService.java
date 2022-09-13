package org.example.telegramBot.service.userService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.telegramBot.security.JwtTokenUtil;
import org.example.telegramBot.service.userService.api.IUserService;
import org.example.telegramBot.service.impl.userService.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final WebClient webClient;
    private final ObjectMapper mapper;

    @Value("${app.userService.url}")
    private String userServiceUrl;

    public UserService(WebClient webClient, ObjectMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
    }

    @Override
    public UserData getUserDataByUserId(Long userId) throws WebClientResponseException{
            logger.info("USER_SERVICE | getUserDataByUserId() invocation, userId - {}", userId);
            return webClient
                    .get()
                    .uri(String.join("/", userServiceUrl, userId.toString()))
                    .retrieve()
                    .bodyToMono(UserData.class)
                    .block();
    }
    @Override
    public void updateUser(UserData userData) throws WebClientException{
        logger.info("USER_SERVICE | updateUser() invocation, userData - {}", userData);
        webClient
                    .put()
                    .uri(userServiceUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(userData))
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
//                    .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
//                    .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new RuntimeException("Internal Server Error")));
    }

    @Override
    public void createUser(UserData userData) throws WebClientException {
        logger.info("USER_SERVICE | createUser() invocation, userData - {}", userData);
        webClient
                    .post()
                    .uri(userServiceUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(userData))
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
//                    .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
//                    .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new RuntimeException("Internal Server Error")));
    }
}

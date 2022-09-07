package com.example.userservice.controllers;

import com.example.userservice.controllers.api.IUserController;
import com.example.userservice.dto.users.UserCreateDto;
import com.example.userservice.dto.users.UserReadAndUpdateDto;
import com.example.userservice.service.UserService;
import com.example.userservice.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController implements IUserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final IUserService service;
    private final ConversionService conversion;

    public UserController(IUserService service, ConversionService conversion) {
        this.service = service;
        this.conversion = conversion;
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<UserReadAndUpdateDto> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(conversion.convert(service.getUser(userId), UserReadAndUpdateDto.class), HttpStatus.OK);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreateDto user) {
        logger.info("CONTROLLER | createUser invocation, user - {}", user);
        service.saveUser(user);
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody UserReadAndUpdateDto dto) {
        service.updateUser(dto);
    }
}

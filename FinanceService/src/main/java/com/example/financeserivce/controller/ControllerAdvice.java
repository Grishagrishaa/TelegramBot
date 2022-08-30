package com.example.financeserivce.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handle(NullPointerException e){
        return e.getMessage();
    }
}

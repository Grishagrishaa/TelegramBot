package org.example.telegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class BotRunner {
    public static void main(String[] args) {
        SpringApplication.run(BotRunner.class, args);
    }
}

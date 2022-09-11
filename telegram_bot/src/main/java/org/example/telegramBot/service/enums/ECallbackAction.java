package org.example.telegramBot.service.enums;

public enum ECallbackAction {
    CONVERT("Converter"), PAGE_SIZE("Message size"), PAGINATION("Number"),
    CITY("City"), RATES("Best Courses");

    private final String message;

    ECallbackAction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

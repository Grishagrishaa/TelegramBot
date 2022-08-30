package org.example.telegramBot.service.keyboard.inlineKeyboard;

import org.example.telegramBot.service.enums.ECallbackAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityInlineKeyboard extends AbstractInlineKeyboard{
    @Value("${app.city.message}")
    private String cityMessage;

    public CityInlineKeyboard(@Value("#{financeServiceBean.getCities()}") List<String> buttons) {
        super(ECallbackAction.CITY, buttons);
    }

    @Override
    public String getMessage() {
        return cityMessage;
    }
}

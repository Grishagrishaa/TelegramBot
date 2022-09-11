package org.example.telegramBot.service.keyboard.inlineKeyboard;

import org.example.telegramBot.service.enums.ECallbackAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyConvertInlineKeyboard extends AbstractInlineKeyboard {
    @Value("${app.convert.message}")
    private String convertMessage;

    public CurrencyConvertInlineKeyboard(@Value("#{financeServiceBean.convertCurrencies}") List<String> buttons) {
        super(ECallbackAction.CONVERT, buttons);
    }

    @Override
    public String getMessage() {
        return convertMessage;
    }
}

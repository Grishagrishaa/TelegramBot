package org.example.telegramBot.service.keyboard.inlineKeyboard;

import org.example.telegramBot.service.enums.ECallbackAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyRatesInlineKeyboard extends AbstractInlineKeyboard {
    @Value("${app.rates.message}")
    private String ratesMessage;

    public CurrencyRatesInlineKeyboard(@Value("#{financeServiceBean.getRatesCurrencies()}") List<String> buttons) {
        super(ECallbackAction.RATES, buttons);
    }

    @Override
    public String getMessage() {
        return ratesMessage;
    }
}

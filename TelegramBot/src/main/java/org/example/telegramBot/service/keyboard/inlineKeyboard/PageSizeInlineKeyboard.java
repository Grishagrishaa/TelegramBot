package org.example.telegramBot.service.keyboard.inlineKeyboard;

import org.example.telegramBot.service.enums.ECallbackAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageSizeInlineKeyboard extends AbstractInlineKeyboard{
    @Value("${app.pagination.message}")
    private String pageSizeMessage;

    public PageSizeInlineKeyboard(@Value("${app.pageSize.keyboard}") List<String> buttons) {
        super(ECallbackAction.PAGE, buttons);
    }

    @Override
    public String getMessage() {
        return pageSizeMessage;
    }
}

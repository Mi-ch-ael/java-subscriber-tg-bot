package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;

public interface Command {
    String commandText();
    String commandRegex();
    String description();
    SendMessage handle(Update update);
    default boolean supports(Update update) {
        if(update.message() == null) return false;
        if(update.message().text() == null) return false;
        System.out.println(update.message().text());
        return update.message().text().matches(commandRegex());
    }
    void setNext(@NotNull Command command);
    Command getNext();
}

package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;

public interface Command {
    String commandText();
    String commandRegex();
    String description();
    default SendMessage handle(Update update) {
        Message message = update.message();
        if(this.supports(update)) {
            return new SendMessage(message.chat().id(), this.response(message))
                    .disableNotification(true);
        }
        if(this.getNext() != null) return this.getNext().handle(update);
        return new SendMessage(message.chat().id(), "Unknown command")
                .disableNotification(true);
    }
    String response(Message message);
    default boolean supports(@NotNull Update update) {
        if(update.message() == null || update.message().text() == null) return false;
        return update.message().text().matches(commandRegex());
    }
    void setNext(@NotNull Command command);
    Command getNext();
}

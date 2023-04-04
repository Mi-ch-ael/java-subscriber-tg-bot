package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class ListCommand extends AbstractCommand{
    @Override
    public String commandText() {
        return "/list";
    }

    @Override
    public String commandRegex() {
        return "/list";
    }

    @Override
    public String description() {
        return "List currently tracked links";
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        String responseText = "Unknown command";
        if(this.supports(update)) {
            responseText = "Handled %s command".formatted(commandText());
        }
        else if(this.next != null) {
            return this.next.handle(update);
        }
        return new SendMessage(message.chat().id(), responseText)
                .disableNotification(true);
    }
}

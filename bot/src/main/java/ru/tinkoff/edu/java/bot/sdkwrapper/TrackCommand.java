package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class TrackCommand extends AbstractCommand{
    @Override
    public String commandText() {
        return "/track <link>";
    }

    @Override
    public String commandRegex() {
        return "/track\\s+[^\\s]+";
    }

    @Override
    public String description() {
        return "Start tracking a link";
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        String responseText = "Unknown command";
        if(this.supports(update)) {
            responseText = "Tracking link %s".formatted(message.text().split("\\s+")[1]);
        }
        else if(this.next != null) {
            return this.next.handle(update);
        }
        return new SendMessage(message.chat().id(), responseText)
                .disableNotification(true);
    }
}

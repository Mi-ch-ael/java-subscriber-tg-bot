package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class StartCommand extends AbstractCommand{
    @Override
    public String commandText() {
        return "/start";
    }

    @Override
    public String commandRegex() {
        return "/start";
    }

    @Override
    public String description() {
        return "Start using the bot by entering this command";
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

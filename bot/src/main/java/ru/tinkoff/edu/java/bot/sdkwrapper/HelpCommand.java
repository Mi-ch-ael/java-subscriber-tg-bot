package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;

public class HelpCommand extends AbstractCommand{
    public HelpCommand(ScrapperClient client) {
        super(client);
    }

    @Override
    public String commandText() {
        return "/help";
    }

    @Override
    public String commandRegex() {
        return "/help";
    }

    @Override
    public String description() {
        return "Show this help";
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        String responseText = "Unknown command";
        if(this.supports(update)) {
            responseText = "";
            Command currentCommand = this;
            while(currentCommand != null) {
                responseText += "%s - %s\n".formatted(currentCommand.commandText(),
                        currentCommand.description());
                currentCommand = currentCommand.getNext();
            }
        }
        else if(this.next != null) {
            return this.next.handle(update);
        }
        return new SendMessage(message.chat().id(), responseText)
                .disableNotification(true);
    }
}

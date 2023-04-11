package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Message;
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
    public String response(Message message) {
        StringBuilder responseBuilder = new StringBuilder();
        Command currentCommand = this;
        while(currentCommand != null) {
            responseBuilder.append("%s - %s\n"
                    .formatted(currentCommand.commandText(), currentCommand.description()));
            currentCommand = currentCommand.getNext();
        }
        return responseBuilder.toString();
    }
}

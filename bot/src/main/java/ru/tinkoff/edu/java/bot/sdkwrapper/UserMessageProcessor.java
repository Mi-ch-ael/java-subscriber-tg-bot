package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserMessageProcessor {
    private final Command firstCommand;
    public UserMessageProcessor(@NotNull List<Command> orderedCommands) {
        if(orderedCommands.size() == 0) throw new IllegalArgumentException("No commands provided");
        for(int i = 0; i < orderedCommands.size()-1; ++i) {
            orderedCommands.get(i).setNext(orderedCommands.get(i+1));
        }
        this.firstCommand = orderedCommands.get(0);
    }
    SendMessage process(Update update) {
        return this.firstCommand.handle(update);
    }
}

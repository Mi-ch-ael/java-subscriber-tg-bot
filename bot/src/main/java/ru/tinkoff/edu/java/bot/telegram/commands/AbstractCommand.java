package ru.tinkoff.edu.java.bot.telegram.commands;

import org.jetbrains.annotations.NotNull;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;

public abstract class AbstractCommand implements Command {
    protected Command next = null;
    protected ScrapperClient client;
    public AbstractCommand(ScrapperClient client) {
        this.client = client;
    }
    @Override
    public void setNext(@NotNull Command command) {
        if(this.next != null) throw new RuntimeException(
                "Resetting `next` field of AbstractCommand successor is forbidden"
        );
        this.next = command;
    }
    public Command getNext() {
        return next;
    }
}

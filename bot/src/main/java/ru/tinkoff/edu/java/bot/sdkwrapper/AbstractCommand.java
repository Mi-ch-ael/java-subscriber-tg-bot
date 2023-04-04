package ru.tinkoff.edu.java.bot.sdkwrapper;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractCommand implements Command {
    protected Command next = null;
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

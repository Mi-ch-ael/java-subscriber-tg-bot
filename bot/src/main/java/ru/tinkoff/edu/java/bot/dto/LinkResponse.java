package ru.tinkoff.edu.java.bot.dto;

import org.jetbrains.annotations.NotNull;

public record LinkResponse(@NotNull Long id, @NotNull String url) {
}

package ru.tinkoff.edu.java.bot.controllers.dto;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record LinkUpdateRequest(@NotNull Long id,
                                @NotNull String url,
                                @NotNull String description,
                                @NotNull List<Long> tgChatIds) {
}

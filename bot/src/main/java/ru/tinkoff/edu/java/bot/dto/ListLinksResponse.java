package ru.tinkoff.edu.java.bot.dto;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ListLinksResponse(@NotNull List<LinkResponse> links, @NotNull Integer size) {
}

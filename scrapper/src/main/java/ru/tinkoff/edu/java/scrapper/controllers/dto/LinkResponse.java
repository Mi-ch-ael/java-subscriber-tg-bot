package ru.tinkoff.edu.java.scrapper.controllers.dto;

import org.jetbrains.annotations.NotNull;

public record LinkResponse(@NotNull Long id, @NotNull String url) {
}

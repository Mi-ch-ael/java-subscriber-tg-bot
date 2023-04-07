package ru.tinkoff.edu.java.scrapper.dto;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ApiErrorResponse(@NotNull String description,
                               @NotNull String code,
                               @NotNull String exceptionName,
                               @NotNull String exceptionMessage,
                               @NotNull List<String> stackTrace) {
}
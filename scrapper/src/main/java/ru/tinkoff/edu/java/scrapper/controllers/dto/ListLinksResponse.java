package ru.tinkoff.edu.java.scrapper.controllers.dto;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ListLinksResponse(@NotNull List<LinkResponse> links, @NotNull Integer size) {
}

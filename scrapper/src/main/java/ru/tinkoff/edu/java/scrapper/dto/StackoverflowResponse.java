package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public record StackoverflowResponse(
        @NotNull StackoverflowResponseItem[] items
) {
    public record StackoverflowResponseItem(
            @NotNull
            @JsonProperty("last_activity_date")
            @JsonFormat(shape = JsonFormat.Shape.NUMBER)
            OffsetDateTime lastActivityAt
    ) {
    }
}

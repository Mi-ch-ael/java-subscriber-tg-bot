package ru.tinkoff.edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public record GithubResponse(@NotNull @JsonProperty("updated_at") OffsetDateTime updatedAt) {
}

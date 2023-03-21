package ru.tinkoff.edu.java.linkParser.parsers;

import org.jetbrains.annotations.NotNull;
import ru.tinkoff.edu.java.linkParser.results.ParseResult;

public interface URLParser {
    ParseResult check(@NotNull String url);
}

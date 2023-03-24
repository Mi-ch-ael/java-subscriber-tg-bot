package ru.tinkoff.edu.java.linkParser.parsers;

import org.jetbrains.annotations.NotNull;
import ru.tinkoff.edu.java.linkParser.results.ParseResult;

public class DefaultURLParser implements URLParser{
    @Override
    public ParseResult parse(@NotNull String url) {
        return null;
    }
    @Override
    public String toString() {
        return "[DefaultURLParser]";
    }
}

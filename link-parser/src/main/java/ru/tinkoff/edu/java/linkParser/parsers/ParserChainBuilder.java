package ru.tinkoff.edu.java.linkParser.parsers;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParserChainBuilder {
    public static URLParser parserChainFrom(@NotNull List<AbstractURLParser> parsers) {
        if(parsers.size() == 0) {
            return new DefaultURLParser();
        }
        for(int i = 0; i < parsers.size()-1; ++i) {
            parsers.get(i).setNext(parsers.get(i+1));
        }
        parsers.get(parsers.size()-1).setNext(new DefaultURLParser());
        return parsers.get(0);
    }

    public static URLParser parserChainFrom() {
        return new DefaultURLParser();
    }
}

package ru.tinkoff.edu.java.linkParser.parsers;

import org.jetbrains.annotations.NotNull;
import ru.tinkoff.edu.java.linkParser.results.ParseResult;
import ru.tinkoff.edu.java.linkParser.results.StackoverflowParseResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackoverflowURLParser extends AbstractURLParser {
    private static final Pattern regex = Pattern.compile(
            "^https://(?:www\\.)?stackoverflow\\.com/questions/([0-9]+)(?:/?|/[-a-zA-Z0-9]+/?)$",
            Pattern.CASE_INSENSITIVE
    );
    @Override
    public ParseResult parse(@NotNull String url) {
        Matcher matcher = regex.matcher(url);
        if(matcher.find()) {
            return new StackoverflowParseResult(matcher.group(1));
        }
        return next.parse(url);
    }
}

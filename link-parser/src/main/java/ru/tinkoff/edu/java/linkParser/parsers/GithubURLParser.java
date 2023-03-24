package ru.tinkoff.edu.java.linkParser.parsers;

import org.jetbrains.annotations.NotNull;
import ru.tinkoff.edu.java.linkParser.results.GithubParseResult;
import ru.tinkoff.edu.java.linkParser.results.ParseResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubURLParser extends AbstractURLParser {
    private static final Pattern regex = Pattern.compile(
            "^https://(?:www\\.)?github\\.com/([-A-Za-z0-9]+)/([-A-Za-z0-9]+)/?$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public ParseResult parse(@NotNull String url) {
        Matcher matcher = regex.matcher(url);
        if(matcher.find()) {
            return new GithubParseResult(matcher.group(1), matcher.group(2));
        }
        return next.parse(url);
    }
}

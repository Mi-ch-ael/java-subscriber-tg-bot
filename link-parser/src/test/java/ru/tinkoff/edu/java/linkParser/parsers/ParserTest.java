package ru.tinkoff.edu.java.linkParser.parsers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.tinkoff.edu.java.linkParser.results.GithubParseResult;
import ru.tinkoff.edu.java.linkParser.results.ParseResult;
import ru.tinkoff.edu.java.linkParser.results.StackoverflowParseResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @ParameterizedTest
    @CsvSource(value = {
            "https://github.com/sanyarnd/tinkoff-java-course-2022/ sanyarnd tinkoff-java-course-2022",
            "https://github.com/sanyarnd/tinkoff-java-course-2022 sanyarnd tinkoff-java-course-2022",
            "https://github.com/SANYARND/tinkoff-java-course-2022/ SANYARND tinkoff-java-course-2022",
            "https://www.github.com/sanyarnd/tinkoff-java-course-2022/ sanyarnd tinkoff-java-course-2022",
            "https://github.com/moevm/adfmp1h23-city-game/ moevm adfmp1h23-city-game"
    }, delimiter = ' ')
    public void githubURLParser_validLink_extractUsernameAndRepoName(
            String link, String username, String repositoryName) {
        URLParser parser = ParserChainBuilder.parserChainFrom(List.of(new GithubURLParser()));

        ParseResult parseResult = parser.parse(link);

        assertNotNull(parseResult);
        assertInstanceOf(GithubParseResult.class, parseResult);
        GithubParseResult githubParseResult = (GithubParseResult) parseResult;
        assertEquals(username, githubParseResult.username());
        assertEquals(repositoryName, githubParseResult.repositoryName());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "https://github.com/moevm/adfmp1h23-city-game/tree/unit",
            "https://github.com/Mi-ch-ael",
            "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c",
            "test",
            ""
    })
    public void githubURLParser_invalidLink_returnNull(String link) {
        URLParser parser = ParserChainBuilder.parserChainFrom(List.of(new GithubURLParser()));

        ParseResult parseResult = parser.parse(link);

        assertNull(parseResult);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c 1642028",
            "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c/ 1642028",
            "https://stackoverflow.com/questions/1642028 1642028",
            "https://stackoverflow.com/questions/1642028/ 1642028"
    }, delimiter = ' ')
    public void stackoverflowURLParser_validLink_extractQuestionId(String link, String id) {
        URLParser parser = ParserChainBuilder.parserChainFrom(List.of(new StackoverflowURLParser()));

        ParseResult parseResult = parser.parse(link);

        assertNotNull(parseResult);
        assertInstanceOf(StackoverflowParseResult.class, parseResult);
        StackoverflowParseResult stackoverflowParseResult = (StackoverflowParseResult) parseResult;
        assertEquals(id, stackoverflowParseResult.questionId());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "https://github.com/sanyarnd/tinkoff-java-course-2022/",
            "https://stackoverflow.com/search?q=unsupported%20link",
            "https://stackoverflow.com/users/11279879/dr-gut",
            "test",
            ""
    })
    public void stackoverflowURLParser_invalidLink_returnNull(String link) {
        URLParser parser = ParserChainBuilder.parserChainFrom(List.of(new StackoverflowURLParser()));

        ParseResult parseResult = parser.parse(link);

        assertNull(parseResult);
    }
}

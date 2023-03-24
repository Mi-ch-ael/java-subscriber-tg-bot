package ru.tinkoff.edu.java.linkParser;

import ru.tinkoff.edu.java.linkParser.parsers.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        URLParser parserChainHead = ParserChainBuilder.parserChainFrom(
                List.of(new StackoverflowURLParser(), new GithubURLParser())
        );
        System.out.println(parserChainHead);
        System.out.println(parserChainHead.parse(
                "https://github.com/sanyarnd/tinkoff-java-course-2022/"
        ));
        System.out.println(parserChainHead.parse(
                "https://github.com/sanyarnd/tinkoff-java-course-2022"
        ));
        System.out.println(parserChainHead.parse(
                "https://github.com/SANYARND/tinkoff-java-course-2022/"
        ));
        System.out.println(parserChainHead.parse(
                "https://www.github.com/sanyarnd/tinkoff-java-course-2022/"
        ));
        System.out.println(parserChainHead.parse(
                "https://github.com/moevm/adfmp1h23-city-game/"
        ));
        System.out.println(parserChainHead.parse(
                "https://github.com/geongeorge/i-hate-regex"
        ));
        System.out.println(parserChainHead.parse(
                "https://github.com/moevm/adfmp1h23-city-game/tree/unit"
        ));
        System.out.println(parserChainHead.parse(
                "https://github.com/Mi-ch-ael/"
        ));
        System.out.println(parserChainHead.parse(
                "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"
        ));
        System.out.println(parserChainHead.parse(
                "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c/"
        ));
        System.out.println(parserChainHead.parse(
                "https://stackoverflow.com/questions/1642028"
        ));
        System.out.println(parserChainHead.parse(
                "https://stackoverflow.com/questions/1642028/"
        ));
        System.out.println(parserChainHead.parse(
                "https://stackoverflow.com/search?q=unsupported%20link"
        ));
        System.out.println(parserChainHead.parse(
                "https://stackoverflow.com/users/11279879/dr-gut"
        ));
        System.out.println(parserChainHead.parse(
                "abcd"
        ));
        System.out.println(parserChainHead.parse(
                ""
        ));
    }
}

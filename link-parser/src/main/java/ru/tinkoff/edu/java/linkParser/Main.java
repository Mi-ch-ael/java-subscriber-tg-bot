package ru.tinkoff.edu.java.linkParser;

import ru.tinkoff.edu.java.linkParser.parsers.DefaultURLParser;
import ru.tinkoff.edu.java.linkParser.parsers.GithubURLParser;
import ru.tinkoff.edu.java.linkParser.parsers.StackoverflowURLParser;
import ru.tinkoff.edu.java.linkParser.parsers.URLParser;

public class Main {
    public static URLParser setup() {
        GithubURLParser githubURLParser = new GithubURLParser();
        StackoverflowURLParser stackoverflowURLParser = new StackoverflowURLParser();
        githubURLParser.setNext(stackoverflowURLParser);
        stackoverflowURLParser.setNext(new DefaultURLParser());
        return githubURLParser;
    }
    public static void main(String[] args) {
        URLParser parserChainHead = setup();
        System.out.println(parserChainHead.check(
                "https://github.com/sanyarnd/tinkoff-java-course-2022/"
        ));
        System.out.println(parserChainHead.check(
                "https://github.com/sanyarnd/tinkoff-java-course-2022"
        ));
        System.out.println(parserChainHead.check(
                "https://github.com/SANYARND/tinkoff-java-course-2022/"
        ));
        System.out.println(parserChainHead.check(
                "https://www.github.com/sanyarnd/tinkoff-java-course-2022/"
        ));
        System.out.println(parserChainHead.check(
                "https://github.com/moevm/adfmp1h23-city-game/"
        ));
        System.out.println(parserChainHead.check(
                "https://github.com/geongeorge/i-hate-regex"
        ));
        System.out.println(parserChainHead.check(
                "https://github.com/moevm/adfmp1h23-city-game/tree/unit"
        ));
        System.out.println(parserChainHead.check(
                "https://github.com/Mi-ch-ael/"
        ));
        System.out.println(parserChainHead.check(
                "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"
        ));
        System.out.println(parserChainHead.check(
                "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c/"
        ));
        System.out.println(parserChainHead.check(
                "https://stackoverflow.com/questions/1642028"
        ));
        System.out.println(parserChainHead.check(
                "https://stackoverflow.com/questions/1642028/"
        ));
        System.out.println(parserChainHead.check(
                "https://stackoverflow.com/search?q=unsupported%20link"
        ));
        System.out.println(parserChainHead.check(
                "https://stackoverflow.com/users/11279879/dr-gut"
        ));
        System.out.println(parserChainHead.check(
                "abcd"
        ));
        System.out.println(parserChainHead.check(
                ""
        ));
    }
}

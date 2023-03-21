package ru.tinkoff.edu.java.linkParser.results;

public sealed interface ParseResult permits GithubParseResult, StackoverflowParseResult {
}

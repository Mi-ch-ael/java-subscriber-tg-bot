package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BotExceptionHandler {
    private static final Map<Class<? extends Exception>, String> errorDescriptions = Map.of(
            HttpMessageNotReadableException.class, "Синтаксическая ошибка в запросе",
            MethodArgumentTypeMismatchException.class, "Неверный тип переданного аргумента",
            MissingServletRequestParameterException.class, "Отсутствует обязательный аргумент",
            HttpMediaTypeNotSupportedException.class, "Неверный тип передаваемых в запросе данных",
            Exception.class, "Внутренняя ошибка сервера"
    );
    private static ApiErrorResponse createResponse(HttpStatus status, Exception exception) {
        return new ApiErrorResponse(
                errorDescriptions.get(exception.getClass()),
                status.toString(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList())
        );
    }
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            HttpMediaTypeNotSupportedException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse badRequestExceptionHandler(Exception exception, WebRequest request) {
        return createResponse(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse globalExceptionHandler(Exception exception, WebRequest request) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }
}

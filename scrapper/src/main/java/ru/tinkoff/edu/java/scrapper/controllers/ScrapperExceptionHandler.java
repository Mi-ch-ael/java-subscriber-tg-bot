package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.tinkoff.edu.java.scrapper.dto.ApiErrorResponse;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ScrapperExceptionHandler {
    private static final Map<HttpStatus, String> errorDescriptions = Map.of(
            HttpStatus.BAD_REQUEST, "Некорректные параметры запроса",
            HttpStatus.NOT_FOUND, "Чат не существует",
            HttpStatus.INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера"
    );
    private static ApiErrorResponse createResponse(HttpStatus status, Exception exception) {
        return new ApiErrorResponse(
                errorDescriptions.get(status),
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

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiErrorResponse notFoundExceptionHandler(Exception exception, WebRequest request) {
        return createResponse(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse globalExceptionHandler(Exception exception, WebRequest request) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }
}

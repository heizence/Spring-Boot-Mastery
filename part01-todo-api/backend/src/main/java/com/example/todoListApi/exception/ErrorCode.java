package com.example.todoListApi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid Input Value"),

    // Todo
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "Todo not found");

    private final HttpStatus status;
    private final String message;
}
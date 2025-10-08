package com.example.todoListApi.exception;

import lombok.Getter;

// A custom exception that is thrown when a client sends wrong requests
@Getter
public class CommonException extends RuntimeException {
    private final ErrorCode errorCode;

    // Constructor that takes a message.
    public CommonException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
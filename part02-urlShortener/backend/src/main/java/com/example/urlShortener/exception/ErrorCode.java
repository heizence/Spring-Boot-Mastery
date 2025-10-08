package com.example.urlShortener.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Defines a specific error code for when a URL is not found.
    URL_NOT_FOUND(HttpStatus.NOT_FOUND, "URL not found");

    private final HttpStatus status;
    private final String message;
}
package com.example.urlShortener.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A generic class to create a common response object (the "wrapper" or "box").
 * This class is immutable because its fields are final.
 *
 * @param <T> the type of the data payload (the "contents")
 */
@Getter
@RequiredArgsConstructor
public class ResponseDto<T> {

    // A status code for the operation. e.g., 200 for success.
    private final int code;

    // A message providing details about the result.
    private final String message;

    // The actual data payload of the response.
    private final T data;

    /**
     * A static factory method for creating a success response with data.
     */
    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(200, "Success", data);
    }

    /**
     * A static factory method for creating a success response without data.
     */
    public static <T> ResponseDto<T> success() {
        return new ResponseDto<>(200, "Success", null);
    }

    /**
     * A static factory method for creating a failure response.
     */
    public static <T> ResponseDto<T> fail(int code, String message) {
        return new ResponseDto<>(code, message, null);
    }

    /**
     * A static factory method for creating a failure response with data (for validation errors).
     */
    public static <T> ResponseDto<T> fail(int code, String message, T data) {
        return new ResponseDto<>(code, message, data);
    }
}
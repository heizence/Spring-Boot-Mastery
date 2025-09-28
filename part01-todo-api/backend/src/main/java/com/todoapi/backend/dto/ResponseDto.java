package com.todoapi.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A generic class to create a common response object.
 * This class is immutable because its fields are final.
 *
 * @param <T> the type of the data payload
 */
@Getter
@RequiredArgsConstructor // Creates a constructor with all final fields.
@Schema(description = "Response DTO for a Todo item")
public class ResponseDto<T> {

    // A status code for the operation. e.g., 1 for success.
    private final int code;

    // A message providing details about the result.
    private final String message;

    // The actual data payload of the response.
    private final T data;

    /**
     * A static factory method for creating a success response with data.
     *
     * @param data The data payload to be included in the response.
     * @param <T>  The type of the data payload.
     * @return A ResponseDto instance indicating success.
     */
    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(200, "Success", data);
    }

    /**
     * A static factory method for creating a success response without data.
     * (e.g., for DELETE or successful PUT operations with no return body).
     *
     * @param <T> The type of the data payload (will be Void).
     * @return A ResponseDto instance indicating success.
     */
    public static <T> ResponseDto<T> success() {
        return new ResponseDto<>(200, "Success", null);
    }

    /**
     * A static factory method for creating a failure response.
     *
     * @param code    The application-specific error code.
     * @param message A descriptive error message.
     * @param <T>     The type of the data payload (will be Void).
     * @return A ResponseDto instance indicating failure.
     */
    public static <T> ResponseDto<T> fail(int code, String message) {
        return new ResponseDto<>(code, message, null);
    }
}
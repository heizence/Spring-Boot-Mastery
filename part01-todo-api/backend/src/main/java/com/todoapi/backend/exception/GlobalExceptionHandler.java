package com.todoapi.backend.exception;

import com.todoapi.backend.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// This annotation makes this class a global exception handler for all @RestController classes.
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles custom business logic exceptions (e.g., TodoNotFoundException).
     *
     * @param ex The CommonException that was thrown.
     * @return A response entity with a specific error message.
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseDto<?>> handleBusinessException(CommonException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        log.error(ex.getMessage());
        ResponseDto<?> response = ResponseDto.fail(errorCode.getStatus().value(), errorCode.getMessage());
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    /**
     * Handles validation exceptions (@Valid annotation).
     * This is triggered when the request body fails validation checks.
     *
     * @param ex The MethodArgumentNotValidException that was thrown.
     * @return A response entity with a map of validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Validation errors: " + errors);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ResponseDto<?> response = ResponseDto.fail(errorCode.getStatus().value(), errorCode.getMessage());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    /**
     * Handles all other unexpected exceptions.
     * This acts as a final safety net.
     *
     * @param ex The Exception that was thrown.
     * @return A response entity with a generic error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleGeneralException(Exception ex) {
        // It's very important to log the full stack trace for unexpected errors.
        ex.printStackTrace();

        ResponseDto<?> response = ResponseDto.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred. Please contact support.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
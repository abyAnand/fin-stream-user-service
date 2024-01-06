package com.finStream.userservice.advice;

import com.finStream.userservice.dto.ApiError;
import com.finStream.userservice.exception.UserNotFoundException;
import com.finStream.userservice.exception.UsernameAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

/**
 * @author Abi Anand
 * @version 1.0.0
 * @since 1.0.0
 */

/**
 * Exception handler dedicated to managing exceptions related to user operations in the application.
 * This class captures and handles specific exceptions that arise during user-related processes
 * such as user creation, retrieval, updating, and deletion.
 * It ensures that these exceptions are responded to with standardized, meaningful responses.
 */

@Slf4j
@ControllerAdvice
public class UserExceptionHandler {


    /**
     * Handles the case where a username already exists in the system.
     * Logs the occurrence and returns an appropriate error response.
     *
     * @param ex The caught UsernameAlreadyExistsException.
     * @param request The WebRequest in context.
     * @return A ResponseEntity containing an ApiError with details about the exception.
     */
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex, WebRequest request) {
        log.error("Username already exists: {}", ex.getMessage());
        ApiError apiError = new ApiError(LocalDate.now(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }


    /**
     * Handles the case where a requested user is not found.
     * Logs the occurrence and returns an appropriate error response.
     *
     * @param ex The caught UserNotFoundException.
     * @param request The WebRequest in context.
     * @return A ResponseEntity containing an ApiError with details about the exception.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        log.error("User not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(LocalDate.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}

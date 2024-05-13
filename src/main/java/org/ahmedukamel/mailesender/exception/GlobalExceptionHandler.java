package org.ahmedukamel.mailesender.exception;

import jakarta.mail.MessagingException;
import org.ahmedukamel.mailesender.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiResponse response = new ApiResponse(false, exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MessagingException.class)
    public ResponseEntity<ApiResponse> handleMessagingException(MessagingException exception) {
        ApiResponse response = new ApiResponse(false, exception.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException exception) {
        ApiResponse response = new ApiResponse(false, exception.getMessage(), exception.getData());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

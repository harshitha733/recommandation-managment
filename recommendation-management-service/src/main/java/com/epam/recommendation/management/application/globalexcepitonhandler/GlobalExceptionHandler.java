package com.epam.recommendation.management.application.globalexcepitonhandler;
import com.epam.recommendation.management.application.exception.DestinationAlreadyExistsException;
import com.epam.recommendation.management.application.exception.EntityNotFoundException;
import com.epam.recommendation.management.application.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>( ApiResponse.<Void>builder().status(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build(),HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ApiResponse<String>> handleJsonProcessingException(JsonProcessingException ex){
        return new ResponseEntity<>(ApiResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message(ex.getOriginalMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ApiResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(ApiResponse.<String>builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ApiResponse.<String>builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(DestinationAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleDestinationAlreadyExistsException(DestinationAlreadyExistsException ex) {
        return new ResponseEntity<>(ApiResponse.<Void>builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).build(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(
                ApiResponse.<Map<String, String>>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Validation failed")
                        .data(errors)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
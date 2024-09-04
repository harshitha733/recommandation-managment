package com.epam.recommendation.management.application.exception.globalexcepitonhandler;
import com.epam.recommendation.management.application.exception.DestinationAlreadyExistsException;
import com.epam.recommendation.management.application.exception.EntityNotFoundException;
import com.epam.recommendation.management.application.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ApiResponse.<Void>builder().status(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ApiResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiResponse<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ApiResponse.<String>builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneralException(Exception ex) {
        return ApiResponse.<String>builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).build();
    }


    @ExceptionHandler(DestinationAlreadyExistsException.class)
    public ApiResponse<?> handleDestinationAlreadyExistsException(DestinationAlreadyExistsException ex) {
        return ApiResponse.<Void>builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).build();
    }
}
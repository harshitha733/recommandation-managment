package com.epam.recommendation.management.application.exception;

public class DestinationAlreadyExistsException extends RuntimeException{
    public DestinationAlreadyExistsException(String message){
        super(message);
    }
}

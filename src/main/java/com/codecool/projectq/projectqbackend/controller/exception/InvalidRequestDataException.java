package com.codecool.projectq.projectqbackend.controller.exception;

public class InvalidRequestDataException extends IllegalArgumentException {
    public InvalidRequestDataException(String message) {
        super(message);
    }
}

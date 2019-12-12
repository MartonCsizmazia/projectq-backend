package com.codecool.projectq.projectqbackend.controller.exception;

public class InvalidOfficeNameException extends InvalidRequestDataException {
    public InvalidOfficeNameException(String officeName) {
        super("Invalid office name: " + officeName);
    }
}

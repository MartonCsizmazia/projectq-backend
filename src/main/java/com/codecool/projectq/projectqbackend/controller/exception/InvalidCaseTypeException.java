package com.codecool.projectq.projectqbackend.controller.exception;

public class InvalidCaseTypeException extends InvalidRequestDataException {
    public InvalidCaseTypeException(String caseTypeDisplayName) {
        super("Invalid case type display name: " + caseTypeDisplayName);
    }
}

package com.workvaluation.workvaluation.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("Resource not found.");
    }
}

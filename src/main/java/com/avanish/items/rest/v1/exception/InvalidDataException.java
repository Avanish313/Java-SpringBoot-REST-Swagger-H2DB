package com.avanish.items.rest.v1.exception;

public class InvalidDataException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public InvalidDataException(final String message) {
        this.message = message;
    }
}

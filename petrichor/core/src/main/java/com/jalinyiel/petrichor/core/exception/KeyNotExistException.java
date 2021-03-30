package com.jalinyiel.petrichor.core.exception;

public class KeyNotExistException extends RuntimeException{
    public KeyNotExistException(String message) {
        super(message);
    }

    public KeyNotExistException() {
    }
}

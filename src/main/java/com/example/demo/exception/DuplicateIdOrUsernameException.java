package com.example.demo.exception;

public class DuplicateIdOrUsernameException extends RuntimeException {

    public DuplicateIdOrUsernameException(String message) {
        super(message);
    }
}

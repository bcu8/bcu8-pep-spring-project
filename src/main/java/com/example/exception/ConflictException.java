package com.example.exception;

public class ConflictException extends HttpException {
    public ConflictException(String message) {
        super(message);
    }
}

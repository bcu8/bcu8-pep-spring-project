package com.example.exception;

// BadRequestException.java
public class BadRequestException extends HttpException {
    public BadRequestException(String message) {
        super(message);
    }
}
package com.example.exception;

public class NotFoundException extends HttpException {
    public NotFoundException(String message) {
        super(message);
    }
}

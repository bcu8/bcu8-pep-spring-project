package com.example.exception;

public class UnauthorizedException extends HttpException {
    public UnauthorizedException(String message) {
        super(message);
    }
}

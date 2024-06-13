package com.example.exception;

// ConflictException.java
public class HttpException extends RuntimeException {
    public HttpException(String message) {
        super(message);
    }
}

package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//this class will implement the returning of appropriate http error status codes to
//clients. The service class will throw the exceptions.

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<String> handleHttpException(HttpException e) {
        //default to internal error http status
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        //map exceptions to their http status if not internal error
        if (e instanceof UnauthorizedException) httpStatus = HttpStatus.UNAUTHORIZED; 
        else if (e instanceof NotFoundException) httpStatus = HttpStatus.NOT_FOUND;
        else if (e instanceof ConflictException) httpStatus = HttpStatus.CONFLICT;
        else if (e instanceof BadRequestException) httpStatus = HttpStatus.BAD_REQUEST;

        //log exception message
        System.out.println(e.getMessage());

        //return appropriate response status for user request
        return ResponseEntity.status(httpStatus).body(e.getMessage());
    }
}
package com.practical.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponseEntity handleCustomException(CustomException ex,WebRequest request) {
        return CustomResponseEntity.builder().code(ex.getHttpStatusCode())
                .status(CustomResponseStatus.FAILURE.getStatus()).message(ex.getMessage()).build();
             
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomResponseEntity handleException(Exception ex,WebRequest request) {
        return CustomResponseEntity.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(CustomResponseStatus.FAILURE.getStatus()).message(ex.getMessage()).build();
             
    }
}

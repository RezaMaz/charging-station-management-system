package com.htb.chargingstationmanagementsystem.controller;

import com.htb.chargingstationmanagementsystem.service.dto.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        var apiError = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(ZonedDateTime.now().toEpochSecond())
                .message(exception.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("\n")))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
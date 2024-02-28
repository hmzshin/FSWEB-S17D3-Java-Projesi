package com.workintech.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @SuppressWarnings("null")
    @ExceptionHandler(KangarooException.class)
    public ResponseEntity<ErrorResponse> handleKangarooException(KangarooException kangarooException) {
        ErrorResponse errorResponse = new ErrorResponse(kangarooException.getStatus().value(),
                kangarooException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, kangarooException.getStatus());
    }

    @SuppressWarnings("null")
    @ExceptionHandler(KoalaException.class)
    public ResponseEntity<ErrorResponse> handleKoalaException(KoalaException koalaException) {
        ErrorResponse errorResponse = new ErrorResponse(koalaException.getStatus().value(),
                koalaException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, koalaException.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

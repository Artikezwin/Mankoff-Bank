package ru.evsmanko.mankoff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>("Content not found", HttpStatus.NO_CONTENT);
    }
}

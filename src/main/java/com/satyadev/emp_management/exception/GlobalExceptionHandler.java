package com.satyadev.emp_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleEmployeNotFound(EmployeeNotFoundException ex) {
        Map<String, Object> response = Map.of(
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage(),
                "status", 404
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}

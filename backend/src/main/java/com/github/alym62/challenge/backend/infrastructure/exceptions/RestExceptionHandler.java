package com.github.alym62.challenge.backend.infrastructure.exceptions;

import com.github.alym62.challenge.backend.application.dto.exceptions.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDetails> handlerBusinessException(BusinessException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .titulo("Business exception")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .detalhes(ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerBusinessException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .titulo("Method Valid exception")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .detalhes(ex.getBindingResult().getFieldError().getDefaultMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

}

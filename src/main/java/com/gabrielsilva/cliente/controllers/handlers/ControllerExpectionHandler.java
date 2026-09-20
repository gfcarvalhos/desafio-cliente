package com.gabrielsilva.cliente.controllers.handlers;

import com.gabrielsilva.cliente.dto.CustomError;
import com.gabrielsilva.cliente.dto.ValidationError;
import com.gabrielsilva.cliente.services.expections.DatabaseException;
import com.gabrielsilva.cliente.services.expections.NoFoundElementException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExpectionHandler {
    
    @ExceptionHandler(NoFoundElementException.class)
    public ResponseEntity<CustomError> noFoundElementException(NoFoundElementException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(request.getRequestURI(), e.getMessage(), status.value(), Instant.now());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> MethodArgumentNotValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        ValidationError err = new ValidationError(request.getRequestURI(), "Dados Inválidos", status.value(), Instant.now());
        for (FieldError f: e.getBindingResult().getFieldErrors()){
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> databaseException(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = e.getMessage();
        CustomError err = new CustomError(request.getRequestURI(), e.getMessage(), status.value(), Instant.now());
        return ResponseEntity.status(status).body(err);
    }

}

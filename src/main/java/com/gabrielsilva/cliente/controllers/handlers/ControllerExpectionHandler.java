package com.gabrielsilva.cliente.controllers.handlers;

import com.gabrielsilva.cliente.dto.CustomError;
import com.gabrielsilva.cliente.services.expections.NoFoundElementException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}

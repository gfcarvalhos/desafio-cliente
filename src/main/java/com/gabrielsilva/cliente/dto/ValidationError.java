package com.gabrielsilva.cliente.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(String path, String error, Integer status, Instant timestamp) {
        super(path, error, status, timestamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError (String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}

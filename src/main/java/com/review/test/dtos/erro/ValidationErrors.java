package com.review.test.dtos.erro;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ValidationErrors extends StandardError {

    private Set<FieldError> fieldErrors = new HashSet<FieldError>();

    public ValidationErrors() {
    }

    public ValidationErrors(Instant timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }

    public Set<FieldError> getFieldErrors() {
        return fieldErrors;
    }


}

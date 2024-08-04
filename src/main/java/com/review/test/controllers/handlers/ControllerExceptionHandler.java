package com.review.test.controllers.handlers;

import com.review.test.dtos.FieldError;
import com.review.test.dtos.StandardError;
import com.review.test.dtos.ValidationErrors;
import com.review.test.services.exceptions.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(UsernameNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> badCredentials(BadCredentialsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Bad credentials");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StandardError> badCredentials(ForbiddenException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Forbidden");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrors validationErrors = new ValidationErrors(Instant.now(), status.value(), e.getMessage()
                , "Validation Error", request.getRequestURI());
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            validationErrors.addFieldError(new FieldError(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.status(status).body(validationErrors);
    }
}

package com.review.test.dtos;

import java.util.Objects;

public class FieldError {

    private String field;
    private String message;


    public FieldError() {
    }


    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }


    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldError that = (FieldError) o;
        return Objects.equals(field, that.field) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, message);
    }
}

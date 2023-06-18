package com.example.echo.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidatorException extends RuntimeException {

    private String message;
    private List<String> errors;

    public ValidatorException(String message) {
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public ValidatorException(List<String> errors) {
        this.message = null;
        this.errors = errors;
    }

    public ValidatorException(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

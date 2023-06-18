package com.example.echo.controller.responses;

import java.util.List;

public class ResponseEntityError {

    private boolean success;
    private String message;
    private List<String> errors;

    public ResponseEntityError(String message, List<String> errors) {
        this.success = false;
        this.message = message;
        this.errors = errors;
    }

}

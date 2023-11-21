package com.bankapi.bankofmikaila.dto;

public class ValidationError {
    private String code;
    private String message;
    public ValidationError() {
    }

    public ValidationError(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

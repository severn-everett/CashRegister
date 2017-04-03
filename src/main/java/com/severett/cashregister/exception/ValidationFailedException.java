package com.severett.cashregister.exception;

public class ValidationFailedException extends Exception {
    
    public ValidationFailedException(String reason) {
        super(String.format("Validation Failed: %s", reason));
    }
    
}

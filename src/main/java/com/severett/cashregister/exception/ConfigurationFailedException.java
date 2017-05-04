package com.severett.cashregister.exception;

public class ConfigurationFailedException extends Exception {
    
    private final Exception causingException;
    
    public ConfigurationFailedException(Exception causingException) {
        super("A failure occurred during configuration instantiation - " + causingException.getMessage());
        this.causingException = causingException;
    }
    
    public Exception getCausingException() {
        return causingException;
    }
}

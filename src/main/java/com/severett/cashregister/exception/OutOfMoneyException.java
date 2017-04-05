package com.severett.cashregister.exception;

public class OutOfMoneyException extends Exception {
    
    public OutOfMoneyException() {
        super("Register has run out of money while processing a transaction!");
    }
    
}

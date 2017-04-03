package com.severett.cashregister;

/**
 *
 * @author Sevay86
 */
public class Main {
 
    public static void main(String[] args) {
        try {
            Register register = new Register(50, 50, 50, 50, 50, 25, 25, 20);
        } catch (Exception e) {
            System.err.println(String.format("Fatal Error in program: %s", e.getMessage()));
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }    
}

package com.severett.cashregister;

import com.severett.cashregister.factory.MoneyCollectionFactory;

/**
 *
 * @author Sevay86
 */
public class Main {
 
    public static void main(String[] args) {
        try {
            MoneyCollectionFactory moneyCollectionFactory = new MoneyCollectionFactory(MoneyCollectionFactory.USD_TYPE);
            Register register = new Register(moneyCollectionFactory);
        } catch (Exception e) {
            System.err.println(String.format("Fatal Error in program: %s", e.getMessage()));
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }    
}

package com.severett.cashregister;

import com.severett.cashregister.config.RegisterConfig;
import com.severett.cashregister.factory.MoneyCollectionFactory;

public class Main {
 
    public static void main(String[] args) {
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/main/resources/registerConfig.yml");
            MoneyCollectionFactory moneyCollectionFactory = new MoneyCollectionFactory(
                    registerConfig.getCollectionType(), registerConfig.getBillAmt(), registerConfig.getCoinAmt());
            Register register = new Register(moneyCollectionFactory);
        } catch (Exception e) {
            System.err.println(String.format("Fatal Error in program: %s", e.getMessage()));
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }    
}

package com.severett.cashregister;

import com.severett.cashregister.exception.ValidationFailedException;

public class Register {
    
    private MoneyCollection moneyCollection;
    
    public Register(int pennies, int nickels, int dimes, int quarters, int dollarBills,
            int fiveDollarBills, int tenDollarBills, int twentyDollarBills) throws ValidationFailedException {
        this(new MoneyCollection(pennies, nickels, dimes, quarters, dollarBills, fiveDollarBills, tenDollarBills, twentyDollarBills));
    }
    
    public Register(MoneyCollection moneyCollection) {
        this.moneyCollection = moneyCollection;
    }
    
    public MoneyCollection getMoneyCollection() {
        return new MoneyCollection(this.moneyCollection);
    }
    
}

package com.severett.cashregister;

import com.severett.cashregister.exception.ValidationFailedException;
import com.severett.cashregister.utils.Validator;

public class MoneyCollection implements Cloneable {

    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private int oneDollarBills;
    private int fiveDollarBills;
    private int tenDollarBills;
    private int twentyDollarBills;
    
    public MoneyCollection(int pennies, int nickels, int dimes, int quarters, int oneDollarBills,
            int fiveDollarBills, int tenDollarBills, int twentyDollarBills) throws ValidationFailedException {
        Validator.requireNotNegative(pennies, "Pennies cannot be negative!");
        this.pennies = pennies;
        Validator.requireNotNegative(nickels, "Nickels cannot be negative!");
        this.nickels = nickels;
        Validator.requireNotNegative(dimes, "Dimes cannot be negative!");
        this.dimes = dimes;
        Validator.requireNotNegative(quarters, "Quarters cannot be negative!");
        this.quarters = quarters;
        Validator.requireNotNegative(oneDollarBills, "One-Dollar Bills cannot be negative!");
        this.oneDollarBills = oneDollarBills;
        Validator.requireNotNegative(fiveDollarBills, "Five-Dollar Bills cannot be negative!");
        this.fiveDollarBills = fiveDollarBills;
        Validator.requireNotNegative(tenDollarBills, "Ten-Dollar Bills cannot be negative!");
        this.tenDollarBills = tenDollarBills;
        Validator.requireNotNegative(twentyDollarBills, "Twenty-Dollar Bills cannot be negative!");
        this.twentyDollarBills = twentyDollarBills;
    }

    /**
     * Copy Constructor. No validation exceptions raised, as copied object is
     * assumed to already be completely validated.
     * @param collectionToCopy 
     */
    public MoneyCollection(MoneyCollection collectionToCopy) {
        this.pennies = collectionToCopy.pennies;
        this.nickels = collectionToCopy.nickels;
        this.dimes = collectionToCopy.dimes;
        this.quarters = collectionToCopy.quarters;
        this.oneDollarBills = collectionToCopy.oneDollarBills;
        this.fiveDollarBills = collectionToCopy.fiveDollarBills;
        this.tenDollarBills = collectionToCopy.tenDollarBills;
        this.twentyDollarBills = collectionToCopy.twentyDollarBills;
    }
    
    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) throws ValidationFailedException {
        Validator.requireNotNegative(pennies, "Pennies cannot be negative!");
        this.pennies = pennies;
    }
    
    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) throws ValidationFailedException {
        Validator.requireNotNegative(nickels, "Nickels cannot be negative!");
        this.nickels = nickels;
    }
    
    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) throws ValidationFailedException {
        Validator.requireNotNegative(dimes, "Dimes cannot be negative!");
        this.dimes = dimes;
    }
    
    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) throws ValidationFailedException {
        Validator.requireNotNegative(quarters, "Quarters cannot be negative!");
        this.quarters = quarters;
    }
    
    public int getOneDollarBills() {
        return oneDollarBills;
    }

    public void setOneDollarBills(int oneDollarBills) throws ValidationFailedException {
        Validator.requireNotNegative(oneDollarBills, "One-Dollar Bills cannot be negative!");
        this.oneDollarBills = oneDollarBills;
    }
    
    public int getFiveDollarBills() {
        return fiveDollarBills;
    }

    public void setFiveDollarBills(int fiveDollarBills) throws ValidationFailedException {
        Validator.requireNotNegative(fiveDollarBills, "Five-Dollar Bills cannot be negative!");
        this.fiveDollarBills = fiveDollarBills;
    }
    
    public int getTenDollarBills() {
        return tenDollarBills;
    }

    public void setTenDollarBills(int tenDollarBills) throws ValidationFailedException {
        Validator.requireNotNegative(tenDollarBills, "Ten-Dollar Bills cannot be negative!");
        this.tenDollarBills = tenDollarBills;
    }
    
    public int getTwentyDollarBills() {
        return twentyDollarBills;
    }

    public void setTwentyDollarBills(int twentyDollarBills) throws ValidationFailedException {
        Validator.requireNotNegative(twentyDollarBills, "Twenty-Dollar Bills cannot be negative!");
        this.twentyDollarBills = twentyDollarBills;
    }

}

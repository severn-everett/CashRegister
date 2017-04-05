package com.severett.cashregister.money;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

final public class MoneyCollection {

    private final Map<MoneyTypes, IntSupplier> getMoneyMap;
    private final Map<MoneyTypes, IntConsumer> setMoneyMap;
    
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private int oneDollarBills;
    private int fiveDollarBills;
    private int tenDollarBills;
    private int twentyDollarBills;
    
    protected MoneyCollection() {
        getMoneyMap = new HashMap<>();
        getMoneyMap.put(MoneyTypes.PENNIES, this::getPennies);
        getMoneyMap.put(MoneyTypes.NICKELS, this::getNickels);
        getMoneyMap.put(MoneyTypes.DIMES, this::getDimes);
        getMoneyMap.put(MoneyTypes.QUARTERS, this::getQuarters);
        getMoneyMap.put(MoneyTypes.ONE_DOLLAR_BILLS, this::getOneDollarBills);
        getMoneyMap.put(MoneyTypes.FIVE_DOLLAR_BILLS, this::getFiveDollarBills);
        getMoneyMap.put(MoneyTypes.TEN_DOLLAR_BILLS, this::getTenDollarBills);
        getMoneyMap.put(MoneyTypes.TWENTY_DOLLAR_BILLS, this::getTwentyDollarBills);
        
        setMoneyMap = new HashMap<>();
        setMoneyMap.put(MoneyTypes.PENNIES, this::setPennies);
        setMoneyMap.put(MoneyTypes.NICKELS, this::setNickels);
        setMoneyMap.put(MoneyTypes.DIMES, this::setDimes);
        setMoneyMap.put(MoneyTypes.QUARTERS, this::setQuarters);
        setMoneyMap.put(MoneyTypes.ONE_DOLLAR_BILLS, this::setOneDollarBills);
        setMoneyMap.put(MoneyTypes.FIVE_DOLLAR_BILLS, this::setFiveDollarBills);
        setMoneyMap.put(MoneyTypes.TEN_DOLLAR_BILLS, this::setTenDollarBills);
        setMoneyMap.put(MoneyTypes.TWENTY_DOLLAR_BILLS, this::setTwentyDollarBills);
    }
    
    public MoneyCollection(int pennies, int nickels, int dimes, int quarters, int oneDollarBills,
            int fiveDollarBills, int tenDollarBills, int twentyDollarBills) {
        this();
        setValue(MoneyTypes.PENNIES, pennies);
        setValue(MoneyTypes.NICKELS, nickels);
        setValue(MoneyTypes.DIMES, dimes);
        setValue(MoneyTypes.QUARTERS, quarters);
        setValue(MoneyTypes.ONE_DOLLAR_BILLS, oneDollarBills);
        setValue(MoneyTypes.FIVE_DOLLAR_BILLS, fiveDollarBills);
        setValue(MoneyTypes.TEN_DOLLAR_BILLS, tenDollarBills);
        setValue(MoneyTypes.TWENTY_DOLLAR_BILLS, twentyDollarBills);
    }

    /**
     * Copy Constructor. No validation exceptions raised, as copied object is
     * assumed to already be completely validated.
     * @param collectionToCopy 
     */
    public MoneyCollection(MoneyCollection collectionToCopy) {
        this();
        this.pennies = collectionToCopy.pennies;
        this.nickels = collectionToCopy.nickels;
        this.dimes = collectionToCopy.dimes;
        this.quarters = collectionToCopy.quarters;
        this.oneDollarBills = collectionToCopy.oneDollarBills;
        this.fiveDollarBills = collectionToCopy.fiveDollarBills;
        this.tenDollarBills = collectionToCopy.tenDollarBills;
        this.twentyDollarBills = collectionToCopy.twentyDollarBills;
    }
    
    public int getValue(MoneyTypes moneyType) {
        return getMoneyMap.get(moneyType).getAsInt();
    }
    
    public void setValue(MoneyTypes moneyType, int value) {
        setMoneyMap.get(moneyType).accept(value);
    }
    
    private int getPennies() {
        return pennies;
    }

    private void setPennies(int pennies) {
        this.pennies = pennies;
    }
    
    private int getNickels() {
        return nickels;
    }

    private void setNickels(int nickels) {
        this.nickels = nickels;
    }
    
    private int getDimes() {
        return dimes;
    }

    private void setDimes(int dimes) {
        this.dimes = dimes;
    }
    
    private int getQuarters() {
        return quarters;
    }

    private void setQuarters(int quarters) {
        this.quarters = quarters;
    }
    
    private int getOneDollarBills() {
        return oneDollarBills;
    }

    private void setOneDollarBills(int oneDollarBills) {
        this.oneDollarBills = oneDollarBills;
    }
    
    private int getFiveDollarBills() {
        return fiveDollarBills;
    }

    private void setFiveDollarBills(int fiveDollarBills) {
        this.fiveDollarBills = fiveDollarBills;
    }
    
    private int getTenDollarBills() {
        return tenDollarBills;
    }

    private void setTenDollarBills(int tenDollarBills) {
        this.tenDollarBills = tenDollarBills;
    }
    
    private int getTwentyDollarBills() {
        return twentyDollarBills;
    }

    private void setTwentyDollarBills(int twentyDollarBills) {
        this.twentyDollarBills = twentyDollarBills;
    }

}

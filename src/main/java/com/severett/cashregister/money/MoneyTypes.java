package com.severett.cashregister.money;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;

public enum MoneyTypes implements IntSupplier {
    PENNIES(()-> 1),
    NICKELS(()-> 5),
    DIMES(()-> 10),
    QUARTERS(()-> 25),
    ONE_DOLLAR_BILLS(()-> 1),
    FIVE_DOLLAR_BILLS(()-> 5),
    TEN_DOLLAR_BILLS(()-> 10),
    TWENTY_DOLLAR_BILLS(()-> 20);
    
    private final IntSupplier valFunc;
    
    private MoneyTypes(final IntSupplier valFunc) {
        this.valFunc = valFunc;
    }
    
    @Override
    public int getAsInt() {
        return valFunc.getAsInt();
    }
    
    public static List<MoneyTypes> getCoins() {
        return Arrays.asList(PENNIES, NICKELS, DIMES, QUARTERS);
    }
    
    public static List<MoneyTypes> getBills() {
        return Arrays.asList(ONE_DOLLAR_BILLS, FIVE_DOLLAR_BILLS, TEN_DOLLAR_BILLS, TWENTY_DOLLAR_BILLS);
    }
}

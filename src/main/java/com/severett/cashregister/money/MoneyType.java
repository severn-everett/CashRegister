package com.severett.cashregister.money;

import java.math.BigDecimal;

public class MoneyType {
    
    public static enum PhysicalType {
        COIN,
        BILL
    }

    private final String name;
    private final BigDecimal value;
    private final PhysicalType physicalType;
    
    public MoneyType(String name, double value, PhysicalType physicalType) {
        this.name = name;
        this.value = BigDecimal.valueOf(value);
        this.physicalType = physicalType;
    }
    
    public String getName() {
        return name;
    }
    
    public BigDecimal getValue() {
        return value;
    }
    
    public PhysicalType getPhysicalType() {
        return physicalType;
    }
    
}

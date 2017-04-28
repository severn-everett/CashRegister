package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyType;
import java.util.Arrays;

public class MockMoneyCollectionFactory implements IMoneyCollectionFactory {
    
    MoneyCollectionBuilder mcb;
    
    public MockMoneyCollectionFactory(int billAmt, int coinAmt) {
        mcb = new MoneyCollectionBuilder(
            "Money",
            Arrays.asList(
                new MoneyType("Pennies", .01, MoneyType.PhysicalType.COIN),
                new MoneyType("Nickels", .05, MoneyType.PhysicalType.COIN),
                new MoneyType("Dimes", .10, MoneyType.PhysicalType.COIN),
                new MoneyType("Quarters", .25, MoneyType.PhysicalType.COIN),
                new MoneyType("One-Dollar Bills", 1, MoneyType.PhysicalType.BILL),
                new MoneyType("Five-Dollar Bills", 5, MoneyType.PhysicalType.BILL),
                new MoneyType("Ten-Dollar Bills", 10, MoneyType.PhysicalType.BILL),
                new MoneyType("Twenty-Dollar Bills", 20, MoneyType.PhysicalType.BILL)
            ),
            billAmt,
            coinAmt
        );
    }

    @Override
    public MoneyCollection generateMoneyCollection() {
        return mcb.buildMoneyCollection();
    }

    @Override
    public MoneyCollection generateMoneyCollection(int billAmt, int coinAmt) {
        return mcb.buildMoneyCollection(billAmt, coinAmt);
    }
    
}

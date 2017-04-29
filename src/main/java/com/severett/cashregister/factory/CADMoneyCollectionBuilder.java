package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CADMoneyCollectionBuilder extends AMoneyCollectionBuilder {
    
    private final List<MoneyType> moneyTypesList;
    
    public CADMoneyCollectionBuilder(int defaultBillAmt, int defaultCoinAmt) {
        super("CAD", defaultBillAmt, defaultCoinAmt);
        moneyTypesList = Collections.unmodifiableList(Arrays.asList(
                    new MoneyType("Pennies", .01, MoneyType.PhysicalType.COIN),
                    new MoneyType("Nickels", .05, MoneyType.PhysicalType.COIN),
                    new MoneyType("Dimes", .10, MoneyType.PhysicalType.COIN),
                    new MoneyType("Quarters", .25, MoneyType.PhysicalType.COIN),
                    new MoneyType("One-Dollar Coins", 1, MoneyType.PhysicalType.COIN),
                    new MoneyType("Two-Dollar Coins", 2, MoneyType.PhysicalType.COIN),
                    new MoneyType("Five-Dollar Bills", 5, MoneyType.PhysicalType.BILL),
                    new MoneyType("Ten-Dollar Bills", 10, MoneyType.PhysicalType.BILL),
                    new MoneyType("Twenty-Dollar Bills", 20, MoneyType.PhysicalType.BILL)
                ));
    }

    @Override
    protected List<MoneyType> getMoneyTypesList() {
        return moneyTypesList;
    }
    
}

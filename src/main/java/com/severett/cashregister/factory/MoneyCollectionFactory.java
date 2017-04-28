package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MoneyCollectionFactory implements IMoneyCollectionFactory {
    
    public static final String USD_TYPE = "USD";
    public static final String CAD_TYPE = "CAD";
    
    private static final Map<String, MoneyCollectionBuilder> CURRENCY_TYPES_MAP;
    
    static {
        CURRENCY_TYPES_MAP = new HashMap<>();
        CURRENCY_TYPES_MAP.put(USD_TYPE,
            new MoneyCollectionBuilder(
                USD_TYPE,
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
                100,
                100
            )
        );
        CURRENCY_TYPES_MAP.put(CAD_TYPE,
            new MoneyCollectionBuilder(
                CAD_TYPE,
                Arrays.asList(
                    new MoneyType("Pennies", .01, MoneyType.PhysicalType.COIN),
                    new MoneyType("Nickels", .05, MoneyType.PhysicalType.COIN),
                    new MoneyType("Dimes", .10, MoneyType.PhysicalType.COIN),
                    new MoneyType("Quarters", .25, MoneyType.PhysicalType.COIN),
                    new MoneyType("One-Dollar Coins", 1, MoneyType.PhysicalType.COIN),
                    new MoneyType("Two-Dollar Coins", 2, MoneyType.PhysicalType.COIN),
                    new MoneyType("Five-Dollar Bills", 5, MoneyType.PhysicalType.BILL),
                    new MoneyType("Ten-Dollar Bills", 10, MoneyType.PhysicalType.BILL),
                    new MoneyType("Twenty-Dollar Bills", 20, MoneyType.PhysicalType.BILL)
                ),
                100,
                100
            )
        );
    }
    
    private MoneyCollectionBuilder designatedCurrencyBuilder;
    
    public MoneyCollectionFactory(String currencyType) throws ClassNotFoundException {
        if (CURRENCY_TYPES_MAP.containsKey(currencyType)) {
            designatedCurrencyBuilder = CURRENCY_TYPES_MAP.get(currencyType);
        } else {
            throw new ClassNotFoundException("Currency Type '" + currencyType + "' Not Found");
        }
    }
    
    @Override
    public MoneyCollection generateMoneyCollection() {
        return designatedCurrencyBuilder.buildMoneyCollection();
    }
    
    @Override
    public MoneyCollection generateMoneyCollection(int billAmt, int coinAmt) {
        return designatedCurrencyBuilder.buildMoneyCollection(billAmt, coinAmt);
    }
    
}

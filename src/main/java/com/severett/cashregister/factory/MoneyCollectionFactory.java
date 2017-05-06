package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MoneyCollectionFactory implements IMoneyCollectionFactory {
    
    private static final Map<String, BiFunction<Integer, Integer, AMoneyCollectionBuilder>> COLLECTION_BUILDER_MAP;
    static {
        COLLECTION_BUILDER_MAP = new HashMap<>();
        COLLECTION_BUILDER_MAP.put("CAD", (dba,dca) -> new CADMoneyCollectionBuilder(dba, dca));
        COLLECTION_BUILDER_MAP.put("USD", (dba,dca) -> new USDMoneyCollectionBuilder(dba, dca));
    }
    
    private final AMoneyCollectionBuilder designatedCurrencyBuilder;
    
    public MoneyCollectionFactory(String currencyType, int defaultBillAmt, int defaultCoinAmt) throws ClassNotFoundException {
        if (COLLECTION_BUILDER_MAP.containsKey(currencyType)) {
            designatedCurrencyBuilder = COLLECTION_BUILDER_MAP.get(currencyType).apply(defaultBillAmt, defaultCoinAmt);
        } else {
            throw new ClassNotFoundException("Collection Type '" + currencyType + "' Not Found!");
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

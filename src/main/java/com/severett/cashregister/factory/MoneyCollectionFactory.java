package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MoneyCollectionFactory implements IMoneyCollectionFactory {
    
    private static final List<AMoneyCollectionBuilder> CURRENCY_TYPES_LIST = Arrays.asList(
        new CADMoneyCollectionBuilder(100, 100),
        new USDMoneyCollectionBuilder(100, 100)
    );
    
    private final AMoneyCollectionBuilder designatedCurrencyBuilder;
    
    public MoneyCollectionFactory(String currencyType) throws ClassNotFoundException {
        Optional<AMoneyCollectionBuilder> collectionBuilder = CURRENCY_TYPES_LIST.stream().filter(mcb -> mcb.getCollectionType().equals(currencyType)).findAny();
        designatedCurrencyBuilder = collectionBuilder.orElseThrow(ClassNotFoundException::new);
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

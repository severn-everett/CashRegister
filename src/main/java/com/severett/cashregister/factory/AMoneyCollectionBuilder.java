package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyType;
import java.util.List;

public abstract class AMoneyCollectionBuilder {
    
    private final String collectionType;
    private final int defaultBillAmt;
    private final int defaultCoinAmt;
    
    public AMoneyCollectionBuilder(String collectionType, int defaultBillAmt, int defaultCoinAmt) {
        this.collectionType = collectionType;
        this.defaultBillAmt = defaultBillAmt;
        this.defaultCoinAmt = defaultCoinAmt;
    }
    
    public final String getCollectionType() {
        return collectionType;
    }
    
    protected abstract List<MoneyType> getMoneyTypesList();
    
    public final MoneyCollection buildMoneyCollection() {
        return new MoneyCollection(getMoneyTypesList(), defaultBillAmt, defaultCoinAmt);
    }
    
    public final MoneyCollection buildMoneyCollection(int billAmt, int coinAmt) {
        return new MoneyCollection(getMoneyTypesList(), billAmt, coinAmt);
    }
}

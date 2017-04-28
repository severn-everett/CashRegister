package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyType;
import java.util.List;

class MoneyCollectionBuilder {
    
    private final String collectionType;
    private final MoneyCollection collectionPrototype;
    private final int defaultBillAmt;
    private final int defaultCoinAmt;
    
    public MoneyCollectionBuilder(String collectionType, List<MoneyType> moneyTypesList, int defaultBillAmt, int defaultCoinAmt) {
        this.collectionType = collectionType;
        this.collectionPrototype = new MoneyCollection(moneyTypesList);
        this.defaultBillAmt = defaultBillAmt;
        this.defaultCoinAmt = defaultCoinAmt;
    }
    
    public String getCollectionType() {
        return collectionType;
    }
    
    public MoneyCollection buildMoneyCollection() {
        return collectionPrototype.clone(defaultBillAmt, defaultCoinAmt);
    }
    
    public MoneyCollection buildMoneyCollection(int billAmt, int coinAmt) {
        return collectionPrototype.clone(billAmt, coinAmt);
    }
}

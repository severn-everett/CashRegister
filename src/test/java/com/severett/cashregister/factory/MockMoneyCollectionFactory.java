package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;

public class MockMoneyCollectionFactory implements IMoneyCollectionFactory {
    
    AMoneyCollectionBuilder mcb;
    
    public MockMoneyCollectionFactory(int billAmt, int coinAmt) {
        mcb = new MockMoneyCollectionBuilder(billAmt, coinAmt);
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

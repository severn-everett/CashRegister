package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;

public interface IMoneyCollectionFactory {
    public MoneyCollection generateMoneyCollection();
    public MoneyCollection generateMoneyCollection(int billAmt, int coinAmt);
}

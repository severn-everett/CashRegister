package com.severett.cashregister;

import com.severett.cashregister.exception.OutOfMoneyException;
import com.severett.cashregister.factory.IMoneyCollectionFactory;
import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Register {
    
    private final IMoneyCollectionFactory moneyCollectionFactory;
    private MoneyCollection moneyReserves;
    
    public Register(IMoneyCollectionFactory moneyCollectionFactory) {
        this.moneyCollectionFactory = moneyCollectionFactory;
        resetMoneyReserves();
    }
    
    public void resetMoneyReserves() {
        moneyReserves = moneyCollectionFactory.generateMoneyCollection();
    }
    
    public MoneyCollection makeChange(BigDecimal amt) throws OutOfMoneyException {
        MoneyCollection change = moneyCollectionFactory.generateMoneyCollection(0, 0);
        Map<MoneyType, Integer> moneyAmt = getMoneyList(moneyReserves.getMoneyTypes(), amt);
        moneyAmt.forEach((moneyType, retAmt)->{
            change.setMoneyTypeAmt(moneyType, retAmt);
            moneyReserves.setMoneyTypeAmt(moneyType, moneyReserves.getMoneyTypeAmt(moneyType) - retAmt);
        });
        return change;
    }
    
    private Map<MoneyType, Integer> getMoneyList(List<MoneyType> moneyTypes, BigDecimal amt) throws OutOfMoneyException {
        Map<MoneyType, Integer> registerAmtsUsed = new HashMap<>();
        for (int i = moneyTypes.size() - 1; i >= 0 && (amt.compareTo(BigDecimal.ZERO) >= 0); i--) {
            MoneyType moneyType = moneyTypes.get(i);
            int registerAmt = moneyReserves.getMoneyTypeAmt(moneyType);
            int currTypeUsed = amt.divideToIntegralValue(moneyType.getValue()).intValue();
            int registerAmtUsed = currTypeUsed <= registerAmt ? currTypeUsed : registerAmt;
            registerAmtsUsed.put(moneyType, registerAmtUsed);
            amt = amt.subtract(new BigDecimal(registerAmtUsed).multiply(moneyType.getValue()));
        }
        if ((amt.compareTo(BigDecimal.ZERO)) > 0) {
            throw new OutOfMoneyException();
        }
        return registerAmtsUsed;
    }
    
}

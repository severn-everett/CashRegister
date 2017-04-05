package com.severett.cashregister;

import com.severett.cashregister.exception.OutOfMoneyException;
import com.severett.cashregister.exception.ValidationFailedException;
import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyTypes;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Register {
    
    private MoneyCollection moneyReserves;
    
    public Register(int pennies, int nickels, int dimes, int quarters, int dollarBills,
            int fiveDollarBills, int tenDollarBills, int twentyDollarBills) {
        this(new MoneyCollection(pennies, nickels, dimes, quarters, dollarBills, fiveDollarBills, tenDollarBills, twentyDollarBills));
    }
    
    public Register(MoneyCollection moneyReserves) {
        this.moneyReserves = moneyReserves;
    }
    
    public MoneyCollection getMoneyReserves() {
        return new MoneyCollection(this.moneyReserves);
    }
    
    public MoneyCollection makeChange(int billAmt, int coinAmt) throws OutOfMoneyException {
        MoneyCollection change = new MoneyCollection(0, 0, 0, 0, 0, 0, 0, 0);
        Map<MoneyTypes, Integer> moneyAmt = Stream.of(
                getMoneyList(MoneyTypes.getCoins(), coinAmt),
                getMoneyList(MoneyTypes.getBills(), billAmt)
            ).map(Map::entrySet)
            .flatMap(Collection::stream)
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    Integer::max
                )
            );
        moneyAmt.forEach((moneyType, amt)->{
            change.setValue(moneyType, amt);
            moneyReserves.setValue(moneyType, moneyReserves.getValue(moneyType) - amt);
        });
        return change;
    }
    
    private Map<MoneyTypes, Integer> getMoneyList(List<MoneyTypes> moneyTypes, int amt) throws OutOfMoneyException {
        Map<MoneyTypes, Integer> registerAmtsUsed = new HashMap<>();
        for (int i = moneyTypes.size() - 1; i >= 0 && amt >= 0; i--) {
            MoneyTypes moneyType = moneyTypes.get(i);
            int registerAmt = moneyReserves.getValue(moneyType);
            int currTypeUsed = amt / moneyType.getAsInt();
            int registerAmtUsed = currTypeUsed <= registerAmt ? currTypeUsed : registerAmt;
            registerAmtsUsed.put(moneyType, registerAmtUsed);
            amt -= (registerAmtUsed * moneyType.getAsInt());
        }
        if (amt > 0) {
            throw new OutOfMoneyException();
        }
        return registerAmtsUsed;
    }
    
}

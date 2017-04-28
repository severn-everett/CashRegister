package com.severett.cashregister.money;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final public class MoneyCollection {
    
    private final Map<MoneyType, Integer> moneyTypeAmts;
    
    public MoneyCollection(List<MoneyType> moneyTypesList) {
        this(moneyTypesList, 0, 0);
    }
    
    private MoneyCollection(List<MoneyType> moneyTypesList, final int billAmt, final int coinAmt) {
        this.moneyTypeAmts = new LinkedHashMap<>(moneyTypesList.stream()
            .collect(
                Collectors.toMap(
                    e->e, e-> e.getPhysicalType().equals(MoneyType.PhysicalType.BILL) ? billAmt : coinAmt
                )
            )
        );
    }
    
    public List<MoneyType> getMoneyTypes() {
        return new ArrayList<>(moneyTypeAmts.keySet());
    }
    
    public int getMoneyTypeAmt(MoneyType moneyType) {
        return moneyTypeAmts.get(moneyType);
    }
    
    public void setMoneyTypeAmt(MoneyType moneyType, int amt) {
        moneyTypeAmts.put(moneyType, amt);
    }
    
    public MoneyCollection clone(int billAmt, int coinAmt) {
        return new MoneyCollection(new ArrayList<>(moneyTypeAmts.keySet()), billAmt, coinAmt);
    }
    
}

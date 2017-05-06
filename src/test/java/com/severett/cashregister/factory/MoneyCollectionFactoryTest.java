package com.severett.cashregister.factory;

import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyType;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class MoneyCollectionFactoryTest {
    
    @Test
    public void testUSMoneyCollection() {
        try {
            MoneyCollectionFactory usMCF = new MoneyCollectionFactory("USD", 100, 100);
            List<MoneyType> moneyTypes = usMCF.generateMoneyCollection().getMoneyTypes();
            Assert.assertEquals(4, moneyTypes.stream().filter(mt -> mt.getPhysicalType() == MoneyType.PhysicalType.COIN).collect(Collectors.toList()).size());
            Assert.assertEquals(4, moneyTypes.stream().filter(mt -> mt.getPhysicalType() == MoneyType.PhysicalType.BILL).collect(Collectors.toList()).size());
        } catch (ClassNotFoundException cnfe) {
            Assert.fail("CNFE Raised: " + cnfe.getMessage());
        }
    }
    
    @Test
    public void testCanadianMoneyCollection() {
        try {
            MoneyCollectionFactory canadianMCF = new MoneyCollectionFactory("CAD", 100, 100);
            List<MoneyType> moneyTypes = canadianMCF.generateMoneyCollection().getMoneyTypes();
            Assert.assertEquals(6, moneyTypes.stream().filter(mt -> mt.getPhysicalType() == MoneyType.PhysicalType.COIN).collect(Collectors.toList()).size());
            Assert.assertEquals(3, moneyTypes.stream().filter(mt -> mt.getPhysicalType() == MoneyType.PhysicalType.BILL).collect(Collectors.toList()).size());
        } catch (ClassNotFoundException cnfe) {
            Assert.fail("CNFE Raised: " + cnfe.getMessage());
        }
    }
    
    @Test
    public void testDefaultMoneyCollectionGeneration() {
        int defaultBillAmt = 20;
        int defaultCoinAmt = 50;
        try {
            MoneyCollectionFactory usMCF = new MoneyCollectionFactory("USD", 100, 100);
            MoneyCollection moneyCollection = usMCF.generateMoneyCollection(defaultBillAmt, defaultCoinAmt);
            List<MoneyType> moneyTypes = moneyCollection.getMoneyTypes();
            Assert.assertFalse(moneyTypes.stream().anyMatch(
                    mt -> mt.getPhysicalType().equals(MoneyType.PhysicalType.COIN) && moneyCollection.getMoneyTypeAmt(mt) != defaultCoinAmt
            ));
            Assert.assertFalse(moneyTypes.stream().anyMatch(
                    mt -> mt.getPhysicalType().equals(MoneyType.PhysicalType.BILL) && moneyCollection.getMoneyTypeAmt(mt) != defaultBillAmt
            ));
        } catch (ClassNotFoundException cnfe) {
            Assert.fail("CNFE Raised: " + cnfe.getMessage());
        }
    }
    
    @Test
    public void testInvalidMoneyCollection() {
        try {
            MoneyCollectionFactory invalidMCF = new MoneyCollectionFactory("Monopoly Money", 100, 100);
            Assert.fail("No OutOfMoneyException raised!");
        } catch (ClassNotFoundException cnfe) {
            // Expected behavior
        }
    }
    
}

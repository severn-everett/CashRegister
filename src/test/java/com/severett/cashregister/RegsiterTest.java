package com.severett.cashregister;

import com.severett.cashregister.exception.OutOfMoneyException;
import com.severett.cashregister.factory.MockMoneyCollectionFactory;
import com.severett.cashregister.money.MoneyCollection;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class RegsiterTest {
    
    @Test
    public void testChangeMade() {
        Register register = new Register(new MockMoneyCollectionFactory(100, 100));
        try {
            MoneyCollection changeMade = register.makeChange(new BigDecimal("12.68"));
            List<Integer> amtsList = changeMade.getMoneyTypes().stream().map(mt -> changeMade.getMoneyTypeAmt(mt)).collect(Collectors.toList());
            
            // Assert that we receive a change set of:
            // * Zero Twenty-Dollar Bills
            // * One Ten-Dollar Bill
            // * Zero Five-Dollar Bills
            // * Two One-Dollar Bills
            // * Two Quarters
            // * One Dime
            // * One Nickel
            // * Three Pennies
            Assert.assertEquals(Arrays.asList(3,1,1,2,2,0,1,0), amtsList);
        } catch (OutOfMoneyException oome) {
            Assert.fail(oome.getMessage());
        }
    }
    
    @Test
    public void testOutOfBills() {
        Register register = new Register(new MockMoneyCollectionFactory(0, 100));
        try {
            MoneyCollection changeMade = register.makeChange(new BigDecimal("50.50"));
            Assert.fail("No OutOfMoneyException raised!");
        } catch (OutOfMoneyException oome) {
            // Expected behavior
        }
    }
    
    @Test
    public void testOutOfCoins() {
        Register register = new Register(new MockMoneyCollectionFactory(100, 0));
        try {
            MoneyCollection changeMade = register.makeChange(new BigDecimal("3.50"));
            Assert.fail("No OutOfMoneyException raised!");
        } catch (OutOfMoneyException oome) {
            // Expected behavior
        }
    }
}

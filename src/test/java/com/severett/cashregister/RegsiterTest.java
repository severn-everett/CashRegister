package com.severett.cashregister;

import com.severett.cashregister.exception.OutOfMoneyException;
import com.severett.cashregister.factory.MockMoneyCollectionFactory;
import com.severett.cashregister.money.MoneyCollection;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class RegsiterTest {
    
    @Test
    public void testChangeMade() {
        Register register = new Register(new MockMoneyCollectionFactory(100, 100));
        try {
            MoneyCollection changeMade = register.makeChange(new BigDecimal("12.68"));
            //Assert.assertEquals(1, changeMade.getValue(MoneyTypes.TEN_DOLLAR_BILLS));
            //Assert.assertEquals(2, changeMade.getValue(MoneyTypes.ONE_DOLLAR_BILLS));
            //Assert.assertEquals(2, changeMade.getValue(MoneyTypes.QUARTERS));
            //Assert.assertEquals(1, changeMade.getValue(MoneyTypes.DIMES));
            //Assert.assertEquals(1, changeMade.getValue(MoneyTypes.NICKELS));
            //Assert.assertEquals(3, changeMade.getValue(MoneyTypes.PENNIES));
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

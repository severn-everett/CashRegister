package com.severett.cashregister;

import com.severett.cashregister.exception.OutOfMoneyException;
import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyTypes;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Sevay86
 */
public class RegsiterTest {
    
    @Test
    public void testChangeMade() {
        Register register = new Register(50, 50, 50, 50, 50, 25, 25, 20);
        try {
            MoneyCollection changeMade = register.makeChange(12, 68);
            Assert.assertEquals(1, changeMade.getValue(MoneyTypes.TEN_DOLLAR_BILLS));
            Assert.assertEquals(2, changeMade.getValue(MoneyTypes.ONE_DOLLAR_BILLS));
            Assert.assertEquals(2, changeMade.getValue(MoneyTypes.QUARTERS));
            Assert.assertEquals(1, changeMade.getValue(MoneyTypes.DIMES));
            Assert.assertEquals(1, changeMade.getValue(MoneyTypes.NICKELS));
            Assert.assertEquals(3, changeMade.getValue(MoneyTypes.PENNIES));
        } catch (OutOfMoneyException oome) {
            Assert.fail(oome.getMessage());
        }
    }
    
    @Test
    public void testOutOfBills() {
        Register register = new Register(50, 50, 50, 50, 10, 3, 0, 0);
        try {
            MoneyCollection changeMade = register.makeChange(50, 50);
            Assert.fail("No OutOfMoneyException raised!");
        } catch (OutOfMoneyException oome) {
        }
    }
    
    @Test
    public void testOutOfCoins() {
        Register register = new Register(10, 3, 0, 0, 50, 50, 50, 50);
        try {
            MoneyCollection changeMade = register.makeChange(3, 50);
            Assert.fail("No OutOfMoneyException raised!");
        } catch (OutOfMoneyException oome) {
        }
    }
}

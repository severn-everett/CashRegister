package com.severett.cashregister.utils;

import com.severett.cashregister.exception.ValidationFailedException;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void testValidationSucceeds() {
        try {
            Validator.requireTrue(()-> 2 != 3, "Test Validation Failed!");
        } catch (ValidationFailedException vfe) {
            Assert.fail("Validation didn't succeed: " + vfe.getMessage());
        }
    }
    
    @Test
    public void testValidationFails() {
        try {
            Validator.requireTrue(()-> (2+2) == 5, "Numbers don't match up!");
            Assert.fail("Validation succeeded when it shouldn't have!");
        } catch (ValidationFailedException vfe) {
            Assert.assertEquals("Validation Failed: Numbers don't match up!", vfe.getMessage());
        }
    }
    
    @Test
    public void testNonNegativeSucceeds() {
        try {
            Validator.requireNotNegative(3, "Number is negative!");
        } catch (ValidationFailedException vfe) {
            Assert.fail("Validation didn't succeed: " + vfe.getMessage());
        }
    }
    
    @Test
    public void testNonNegativeFails() {
        try {
            Validator.requireNotNegative(-3, "Number is negative!");
            Assert.fail("Validation succeeded when it shouldn't have!");
        } catch (ValidationFailedException vfe) {
            Assert.assertEquals("Validation Failed: Number is negative!", vfe.getMessage());
        }
    }
}

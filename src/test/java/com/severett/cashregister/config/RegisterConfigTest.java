package com.severett.cashregister.config;

import com.severett.cashregister.exception.ConfigurationFailedException;
import com.severett.cashregister.exception.ValidationFailedException;
import javax.naming.ConfigurationException;
import org.junit.Assert;
import org.junit.Test;

public class RegisterConfigTest {

    @Test
    public void testNormalRegisterConfig() {
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/test/resources/goodConfigs/normalUSDConfig.yml");
            Assert.assertEquals("USD", registerConfig.getCollectionType());
            Assert.assertEquals(200, registerConfig.getCoinAmt());
            Assert.assertEquals(150, registerConfig.getBillAmt());
        } catch (ConfigurationFailedException cfe) {
            Assert.fail("Unexpected ConfigurationFailedException Raised: " + cfe.getCausingException().getMessage());
        }
    }
    
    @Test
    public void testNoBillSettings() {
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/test/resources/goodConfigs/noBillSettings.yml");
            Assert.assertEquals("CAD", registerConfig.getCollectionType());
            Assert.assertEquals(125, registerConfig.getCoinAmt());
            Assert.assertEquals(100, registerConfig.getBillAmt());
        } catch (ConfigurationFailedException cfe) {
            Assert.fail("Unexpected ConfigurationFailedException Raised: " + cfe.getCausingException().getMessage());
        }
    }
    
    @Test
    public void testNoCoinSettings() {
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/test/resources/goodConfigs/noCoinSettings.yml");
            Assert.assertEquals("CAD", registerConfig.getCollectionType());
            Assert.assertEquals(100, registerConfig.getCoinAmt());
            Assert.assertEquals(150, registerConfig.getBillAmt());
        } catch (ConfigurationFailedException cfe) {
            Assert.fail("Unexpected ConfigurationFailedException Raised: " + cfe.getCausingException().getMessage());
        }
    }
    
    @Test
    public void testNoCollectionType() {
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/test/resources/badConfigs/noCollectionType.yml");
            Assert.fail("No exception raised when a ConfigurationFailedException was expected!");
        } catch (ConfigurationFailedException cfe) {
            Assert.assertEquals(ConfigurationException.class.toString(), cfe.getCausingException().getClass().toString());
            Assert.assertEquals("Entry 'collectionType' is required", cfe.getCausingException().getMessage());
        }
    }
    
    @Test
    public void testBadAmtValue() {
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/test/resources/badConfigs/amtInvalid.yml");
            Assert.fail("No exception raised when a ConfigurationFailedException was expected due to an invalid amount type");
        } catch (ConfigurationFailedException cfe) {
            Assert.assertEquals(NumberFormatException.class.toString(), cfe.getCausingException().getClass().toString());
        }
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/test/resources/badConfigs/amtNegative.yml");
            Assert.fail("No exception raised when a ConfigurationFailedException was expected due to a negative amount");
        } catch (ConfigurationFailedException cfe) {
            Assert.assertEquals(ValidationFailedException.class.toString(), cfe.getCausingException().getClass().toString());
        }
        try {
            RegisterConfig registerConfig = new RegisterConfig("src/test/resources/badConfigs/amtZero.yml");
            Assert.fail("No exception raised when a ConfigurationFailedException was expected due to an amount equaling zero");
        } catch (ConfigurationFailedException cfe) {
            Assert.assertEquals(ValidationFailedException.class.toString(), cfe.getCausingException().getClass().toString());
        }
    }
}

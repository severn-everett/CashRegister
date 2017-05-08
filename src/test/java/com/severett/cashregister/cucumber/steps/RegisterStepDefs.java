package com.severett.cashregister.cucumber.steps;

import com.severett.cashregister.Register;
import com.severett.cashregister.config.RegisterConfig;
import com.severett.cashregister.factory.IMoneyCollectionFactory;
import com.severett.cashregister.factory.MoneyCollectionFactory;
import com.severett.cashregister.money.MoneyCollection;
import com.severett.cashregister.money.MoneyType;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.Assert;

public class RegisterStepDefs implements En {
    
    private static final Map<String, String> CONFIGURATION_MAP;
    static {
        CONFIGURATION_MAP = new HashMap<>();
        CONFIGURATION_MAP.put("normalUSDConfig.yml", "src/test/resources/goodConfigs/normalUSDConfig.yml");
        CONFIGURATION_MAP.put("normalCADConfig.yml", "src/test/resources/goodConfigs/normalCADConfig.yml");
        CONFIGURATION_MAP.put("limitedMoney.yml", "src/test/resources/goodConfigs/limitedMoney.yml");
    }
    
    private Register register;
    private MoneyCollection changeAmt;
    private List<MoneyType> moneyTypesList;
    private Exception caughtException;
    
    public RegisterStepDefs() throws Exception {
        Given("^I have a register using configuration \"([^\"]*)\"$", (String configurationFileName) ->{
            try {
                String configFilePath = CONFIGURATION_MAP.entrySet()
                        .stream()
                        .filter((e) -> e.getKey().equals(configurationFileName))
                        .map((e) -> e.getValue())
                        .findFirst()
                        .orElseThrow(()-> new NoSuchElementException("Configuration file '" + configurationFileName + "' not recognized"));
                RegisterConfig registerConfig = new RegisterConfig(configFilePath);
                IMoneyCollectionFactory moneyCollectionFactory = new MoneyCollectionFactory(registerConfig.getCollectionType(), registerConfig.getBillAmt(), registerConfig.getCoinAmt());
                register = new Register(moneyCollectionFactory);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        Given("^I request \\$(\\d+)\\.(\\d+) in change$", (Integer dollars, Integer cents) ->{
            try {
                BigDecimal moneyRequestAmt = new BigDecimal(String.format("%02d.%02d", dollars, cents));
                changeAmt = register.makeChange(moneyRequestAmt);
                moneyTypesList = changeAmt.getMoneyTypes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        Given("^I try to request \\$(\\d+)\\.(\\d+) in change$", (Integer dollars, Integer cents) -> {
            try {
                BigDecimal moneyRequestAmt = new BigDecimal(String.format("%02d.%02d", dollars, cents));
                changeAmt = register.makeChange(moneyRequestAmt);
                Assert.fail("An exception was expected while processing " + moneyRequestAmt + " but didn't occur");
            } catch (Exception e) {
                caughtException = e;
            }    
        });
        
        Then("^I should get the following monetary amounts:$", (DataTable amtsTable) -> {
            try {
                List<Map<String, Integer>> amtsMapList = amtsTable.asMaps(String.class, Integer.class);
                amtsMapList.get(0).forEach((amtName, amt) -> {
                    MoneyType foundMoneyType = moneyTypesList.stream()
                            .filter(mt -> mt.getName().equals(amtName))
                            .findFirst()
                            .orElseThrow(() -> new NoSuchElementException("Money Type '" + amtName + "' not found"));              
                    int changeTypeAmt = changeAmt.getMoneyTypeAmt(foundMoneyType);
                    Assert.assertTrue(amtName + " is not " + amt + ", actually " + changeTypeAmt, (amt.equals(changeTypeAmt)));
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        Then("^I should get an instance of \"([^\"]*)\"$", (String exceptionType) -> {
            if (caughtException != null) {
                Assert.assertEquals(exceptionType, caughtException.getClass().getSimpleName());
            } else {
                Assert.fail("No exception has been caught");
            }
        });
    }
}

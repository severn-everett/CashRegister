package com.severett.cashregister.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.severett.cashregister.exception.ConfigurationFailedException;
import com.severett.cashregister.exception.ValidationFailedException;
import com.severett.cashregister.money.MoneyType;
import com.severett.cashregister.utils.Validator;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import javax.naming.ConfigurationException;

public final class RegisterConfig {
    
    private static final int DEFAULT_COIN_AMT = 100;
    private static final int DEFAULT_BILL_AMT = 100;
    
    private String collectionType;
    private int coinAmt;
    private int billAmt;
    
    public RegisterConfig(String configFileName) throws ConfigurationFailedException {
        readConfigFile(configFileName);
        validateConfiguration();
    }
    
    private void readConfigFile(String configFileName) throws ConfigurationFailedException {
        try (Reader r = new FileReader(configFileName)) {
            YamlReader reader = new YamlReader(r);
            Map configMap = (Map) reader.read();
            if (configMap.containsKey("collectionType")) {
                collectionType = (String) configMap.get("collectionType");
                int coinAmtSetting = DEFAULT_COIN_AMT;
                int billAmtSetting = DEFAULT_BILL_AMT;
                List defaultAmts = (List) configMap.get("defaultAmt");
                for (Object da : defaultAmts) {
                    Map defaultAmtMap = (Map) da;
                    if ((defaultAmtMap.containsKey("type")) && (defaultAmtMap.containsKey("amt"))) {
                        String amtType = (String) defaultAmtMap.get("type");
                        if (amtType.equals(MoneyType.PhysicalType.COIN.name())) {
                            coinAmtSetting = Integer.valueOf(defaultAmtMap.get("amt").toString());
                        } else if (amtType.equals(MoneyType.PhysicalType.BILL.name())) {
                            billAmtSetting = Integer.valueOf(defaultAmtMap.get("amt").toString());
                        }
                    }
                }
                coinAmt = coinAmtSetting;
                billAmt = billAmtSetting;
            } else {
                throw new ConfigurationFailedException(new ConfigurationException("Entry 'collectionType' is required"));
            }
        } catch (IOException | NumberFormatException e) {
            throw new ConfigurationFailedException(e);
        }
    }
    
    private void validateConfiguration() throws ConfigurationFailedException {
        try {
            Validator.requireTrue(()->coinAmt > 0, "Default coin amount must be greater than zero");
            Validator.requireTrue(()->billAmt > 0, "Default bill amount must be greater than zero");
        } catch (ValidationFailedException vfe) {
            throw new ConfigurationFailedException(vfe);
        }
    }
    
    public String getCollectionType() {
        return collectionType;
    }
    
    public int getCoinAmt() {
        return coinAmt;
    }
    
    public int getBillAmt() {
        return billAmt;
    }
    
}

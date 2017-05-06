package com.severett.cashregister.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.severett.cashregister.exception.ConfigurationFailedException;
import com.severett.cashregister.money.MoneyType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import javax.naming.ConfigurationException;

public final class RegisterConfig {
    
    private static final int DEFAULT_COIN_AMT = 100;
    private static final int DEFAULT_BILL_AMT = 100;
    
    private final String collectionType;
    private final int coinAmt;
    private final int billAmt;
    
    public RegisterConfig(String configFileName) throws ConfigurationFailedException {
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
        } catch (IOException ioe) {
            throw new ConfigurationFailedException(ioe);
        } catch (NumberFormatException nfe) {
            throw new ConfigurationFailedException(nfe);
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

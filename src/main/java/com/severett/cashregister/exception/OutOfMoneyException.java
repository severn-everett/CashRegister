package com.severett.cashregister.exception;

import java.math.BigDecimal;

public class OutOfMoneyException extends Exception {
    
    private final BigDecimal originalAmt;
    private final BigDecimal resultingAmt;
    
    public OutOfMoneyException(BigDecimal originalAmt, BigDecimal resultingAmt) {
        super("Register only has run out of money: short " + resultingAmt + " out of " + originalAmt);
        this.originalAmt = originalAmt;
        this.resultingAmt = resultingAmt;
    }
    
    public BigDecimal getOriginalAmt() {
        return originalAmt;
    }
    
    public BigDecimal getResultingAmt() {
        return resultingAmt;
    }
}

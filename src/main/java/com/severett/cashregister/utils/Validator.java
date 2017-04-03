package com.severett.cashregister.utils;

import com.severett.cashregister.exception.ValidationFailedException;
import java.util.function.BooleanSupplier;

public class Validator {

    public static void requireNotNegative(int num, String errorMessage) throws ValidationFailedException {
        requireTrue(()-> num >= 0, errorMessage);
    }
    
    public static void requireTrue(BooleanSupplier predicate, String errorMessage) throws ValidationFailedException {
        if (!predicate.getAsBoolean()) {
            throw new ValidationFailedException(errorMessage);
        }
    }
    
}

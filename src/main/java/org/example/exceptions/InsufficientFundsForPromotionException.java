package org.example.exceptions;

public class InsufficientFundsForPromotionException extends Exception{
    public InsufficientFundsForPromotionException(String error) {
        super(error);
    }
}

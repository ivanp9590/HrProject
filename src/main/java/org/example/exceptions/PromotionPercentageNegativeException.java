package org.example.exceptions;

public class PromotionPercentageNegativeException extends Exception {
    public PromotionPercentageNegativeException(String error) {
        super(error);
    }
}

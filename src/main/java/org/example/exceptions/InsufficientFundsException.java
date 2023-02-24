package org.example.exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String error) {
        super(error);
    }
}

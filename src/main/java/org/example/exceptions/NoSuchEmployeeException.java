package org.example.exceptions;

public class NoSuchEmployeeException extends Exception{
    public NoSuchEmployeeException(String error) {
        super(error);
    }
}

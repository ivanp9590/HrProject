package org.example.exceptions;

public class NoSuchDepartmentException extends Exception {
    public NoSuchDepartmentException(String error) {
        super(error);
    }
}

package org.example.exceptions;

public class DepartmentAlreadyExistsException extends Exception {
    public DepartmentAlreadyExistsException(String error){
        super(error);
    }
}

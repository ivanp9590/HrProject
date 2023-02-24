package org.example.interfaces;

import org.example.exceptions.InsufficientFundsForPromotionException;
import org.example.exceptions.NoSuchEmployeeException;

import java.util.HashMap;
import java.util.NoSuchElementException;

public interface EmployeeSupport {
    public void hireEmployee(int employeeId, String employeeName, int salary);
    public void showEmployee(int employeeId, AssignmentSupport assignmentSupport) throws NoSuchEmployeeException;

    void promoteEmployee(int employeeId, double promotionPercentage, AssignmentSupport assignmentSupport) throws InsufficientFundsForPromotionException;

    public HashMap<Integer, Employee> getEmployees();
}

package org.example.interfaces;

import org.example.exceptions.InsufficientFundsForPromotionException;
import org.example.exceptions.NoSuchEmployeeException;
import org.example.exceptions.PromotionPercentageNegativeException;

import java.util.HashMap;
import java.util.NoSuchElementException;

public interface EmployeeSupport {
    public void hireEmployee(int employeeId, String employeeName, double salary);
    public String showEmployee(int employeeId, AssignmentSupport assignmentSupport) throws NoSuchEmployeeException;

    void promoteEmployee(int employeeId, int promotionPercentage, AssignmentSupport assignmentSupport, DepartmentSupport departmentSupport) throws InsufficientFundsForPromotionException, PromotionPercentageNegativeException;

    Employee getEmployeeById(int employeeId);

    public HashMap<Integer, Employee> getEmployees();
}

package org.example.interfaces;

import org.example.exceptions.InsufficientFundsException;

public interface AssignmentSupport {
    public void assignEmployeeToDepartment(int employeeId, int departmentId) throws InsufficientFundsException;

    public String getHiredEmployees(int departmentId);

    public String getHiredEmployee(int employeeId);
}

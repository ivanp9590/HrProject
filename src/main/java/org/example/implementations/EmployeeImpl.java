package org.example.implementations;

import org.example.interfaces.Employee;

public class EmployeeImpl implements Employee {
    private int employeeId;
    private String employeeName;
    private double salary;
    public EmployeeImpl(int employeeId, String employeeName, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
    }

    @Override
    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String getEmployeeName() {
        return employeeName;
    }

    @Override
    public double getEmployeeSalary() {
        return salary;
    }

    @Override
    public void setEmployeeSalary(double salary) {
        this.salary = salary;
    }
}

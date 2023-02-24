package org.example.implementations;

import org.example.interfaces.Department;

public class DepartmentImpl implements Department {
    private int departmentId;
    private String departmentName;
    private int yearlyBudget;

    private int remainingBudget;
    public DepartmentImpl(int departmentId, String departmentName, int yearlyBudget) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.yearlyBudget = yearlyBudget;
        this.remainingBudget = yearlyBudget;
    }

    @Override
    public int getDepartmentId() {
        return departmentId;
    }

    @Override
    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public int getYearlyBudget() {
        return yearlyBudget;
    }

    @Override
    public int getRemainingBudget() {
        return remainingBudget;
    }

    @Override
    public void updateBudget(double expense) {
        this.remainingBudget -= expense;
    }
}

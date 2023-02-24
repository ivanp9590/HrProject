package org.example.implementations;

import org.example.interfaces.Department;

public class DepartmentImpl implements Department {
    private int departmentId;
    private String departmentName;
    private double yearlyBudget;

    private double remainingBudget;
    public DepartmentImpl(int departmentId, String departmentName, double yearlyBudget) {
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
    public double getYearlyBudget() {
        return yearlyBudget;
    }

    @Override
    public double getRemainingBudget() {
        return remainingBudget;
    }

    @Override
    public void setYearlyBudget(int budget) {
        this.yearlyBudget = budget;
        this.remainingBudget = budget;
    }

    @Override
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public void updateBudget(double remaining) {
        this.remainingBudget = remaining;
    }
}

package org.example.interfaces;

public interface Department {
    int getDepartmentId();

    String getDepartmentName();

    double getYearlyBudget();

    double getRemainingBudget();

    void setYearlyBudget(int budget);

    void setDepartmentName(String departmentName);

    void updateBudget(double remaining);
}

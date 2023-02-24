package org.example.interfaces;

public interface Department {
    int getDepartmentId();

    String getDepartmentName();

    int getYearlyBudget();

    int getRemainingBudget();

    void updateBudget(double expense);
}

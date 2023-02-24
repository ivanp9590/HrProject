package org.example.interfaces;

import org.example.exceptions.NoSuchDepartmentException;

import java.util.HashMap;

public interface DepartmentSupport {
    public void showDepartment(int departmentId, AssignmentSupport assignmentSupport) throws NoSuchDepartmentException;
    public void createDepartment(int departmentId, String departmentName, int yearlyBudget);
    public HashMap<Integer, Department> getDepartments();
}

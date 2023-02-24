package org.example.interfaces;

import org.example.exceptions.DepartmentAlreadyExistsException;
import org.example.exceptions.NoSuchDepartmentException;

import java.util.HashMap;

public interface DepartmentSupport {
    public String showDepartment(int departmentId, AssignmentSupport assignmentSupport) throws NoSuchDepartmentException;
    public void createDepartment(int departmentId, String departmentName, double yearlyBudget);

    void updateDepartment(int departmentId, String departmentName, int yearlyBudget) throws DepartmentAlreadyExistsException;

    Department getSpecificDepartment(int departmentId);

    public HashMap<Integer, Department> getDepartments();
}

package org.example.implementations;

import org.example.exceptions.NoSuchDepartmentException;
import org.example.exceptions.NoSuchEmployeeException;
import org.example.interfaces.AssignmentSupport;
import org.example.interfaces.Department;
import org.example.interfaces.DepartmentSupport;

import java.util.HashMap;

public class DepartmentSupportImpl implements DepartmentSupport {

    HashMap<Integer, Department> departmentHashMap = new HashMap<Integer, Department>();

    @Override
    public void showDepartment(int departmentId, AssignmentSupport assignmentSupport) throws NoSuchDepartmentException {
        if(departmentHashMap.containsKey(departmentId)) {
            Department foundDepartment = departmentHashMap.get(departmentId);
            String departmentString = String.format(
                    "Department: %s\nID: %s\nBudget: %d$/year\nNot allocated: %d\nEmployees: %s",
                    foundDepartment.getDepartmentName(),
                    foundDepartment.getDepartmentId(),
                    foundDepartment.getYearlyBudget(),
                    foundDepartment.getRemainingBudget(),
                    assignmentSupport.getHiredEmployees(foundDepartment.getDepartmentId())
            );
            System.out.println(departmentString);
        } else {
            String errorString = String.format("Department with ID %d does not exist!", departmentId);
            throw new NoSuchDepartmentException(errorString);
        }
    }

    @Override
    public void createDepartment(int departmentId, String departmentName, int yearlyBudget) {
        Department newDepartment = new DepartmentImpl(departmentId, departmentName, yearlyBudget);
        departmentHashMap.put(departmentId, newDepartment);
    }

    @Override
    public HashMap<Integer, Department> getDepartments() {
        return this.departmentHashMap;
    }
}

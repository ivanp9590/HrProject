package org.example.implementations;

import org.example.exceptions.DepartmentAlreadyExistsException;
import org.example.exceptions.NoSuchDepartmentException;
import org.example.interfaces.AssignmentSupport;
import org.example.interfaces.Department;
import org.example.interfaces.DepartmentSupport;

import java.util.HashMap;

public class DepartmentSupportImpl implements DepartmentSupport {

    HashMap<Integer, Department> departmentHashMap = new HashMap<Integer, Department>();

    @Override
    public String showDepartment(int departmentId, AssignmentSupport assignmentSupport) throws NoSuchDepartmentException {
        if(departmentHashMap.containsKey(departmentId)) {
            Department foundDepartment = departmentHashMap.get(departmentId);
            String departmentString = String.format(
                    "Department: %s\nID: %s\nBudget: %.0f$/year\nNot allocated: %.0f\nEmployees: %s",
                    foundDepartment.getDepartmentName(),
                    foundDepartment.getDepartmentId(),
                    foundDepartment.getYearlyBudget(),
                    foundDepartment.getRemainingBudget(),
                    assignmentSupport.getHiredEmployees(foundDepartment.getDepartmentId())
            );
            System.out.println(departmentString);
            return departmentString;
        } else {
            String errorString = String.format("Department with ID %d does not exist!", departmentId);
            throw new NoSuchDepartmentException(errorString);
        }
    }

    @Override
    public void createDepartment(int departmentId, String departmentName, double yearlyBudget) {
        Department newDepartment = new DepartmentImpl(departmentId, departmentName, yearlyBudget);
        departmentHashMap.put(departmentId, newDepartment);
    }

    @Override
    public void updateDepartment(int departmentId, String departmentName, int yearlyBudget) throws DepartmentAlreadyExistsException {
        if(getDepartments().containsKey(departmentId)) {
           Department departmentForUpdate = getDepartments().get(departmentId);
           departmentForUpdate.setYearlyBudget(yearlyBudget);
           departmentForUpdate.setDepartmentName(departmentName);
        } else {
            String errorMessage = String.format("No department with ID %d exists!", departmentId);
            throw new DepartmentAlreadyExistsException(errorMessage);
        }
    }

    @Override
    public Department getSpecificDepartment(int departmentId) {
        return this.departmentHashMap.get(departmentId);
    }

    @Override
    public HashMap<Integer, Department> getDepartments() {
        return this.departmentHashMap;
    }
}

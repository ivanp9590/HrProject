package org.example.implementations;

import org.example.exceptions.InsufficientFundsForPromotionException;
import org.example.exceptions.NoSuchEmployeeException;
import org.example.interfaces.AssignmentSupport;
import org.example.interfaces.Employee;
import org.example.interfaces.EmployeeSupport;

import java.util.HashMap;

public class EmployeeSupportImpl implements EmployeeSupport {
    HashMap<Integer, Employee> employeeHashMap = new HashMap<Integer, Employee>();

    @Override
    public void hireEmployee(int employeeId, String employeeName, int salary) {
        Employee newEmployee = new EmployeeImpl(employeeId, employeeName, salary);
        employeeHashMap.put(employeeId, newEmployee);
    }

    @Override
    public void showEmployee(int employeeId, AssignmentSupport assignmentSupport) throws NoSuchEmployeeException {
        if(employeeHashMap.containsKey(employeeId)) {
            Employee foundEmployee = employeeHashMap.get(employeeId);
            String employeeString = String.format(
                    "Employee ID: %d, name: %s\nDepartment: %s, Salary: %.2f",
                    foundEmployee.getEmployeeId(),
                    foundEmployee.getEmployeeName(),
                    assignmentSupport.getHiredEmployee(employeeId),
                    foundEmployee.getEmployeeSalary()
            );
            System.out.println(employeeString);
        } else {
            String errorString = String.format("Employee with ID %d does not exist!", employeeId);
            throw new NoSuchEmployeeException(errorString);
        }
    }

    @Override
    public void promoteEmployee(int employeeId, double promotionPercentage, AssignmentSupport assignmentSupport) throws InsufficientFundsForPromotionException {
        Employee foundEmployee = employeeHashMap.get(employeeId);
        String department = assignmentSupport.getHiredEmployee(employeeId);
        if (department.equals("N/A")) {
            foundEmployee.setEmployeeSalary(foundEmployee.getEmployeeSalary() + (promotionPercentage * 100) / foundEmployee.getEmployeeSalary());
            employeeHashMap.put(employeeId, foundEmployee);
        }
    }

    @Override
    public HashMap<Integer, Employee> getEmployees() {
        return this.employeeHashMap;
    }
}

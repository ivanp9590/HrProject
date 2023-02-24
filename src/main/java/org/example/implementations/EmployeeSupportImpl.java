package org.example.implementations;

import org.example.exceptions.InsufficientFundsForPromotionException;
import org.example.exceptions.NoSuchEmployeeException;
import org.example.exceptions.PromotionPercentageNegativeException;
import org.example.interfaces.*;

import java.util.HashMap;

public class EmployeeSupportImpl implements EmployeeSupport {
    HashMap<Integer, Employee> employeeHashMap = new HashMap<Integer, Employee>();

    @Override
    public void hireEmployee(int employeeId, String employeeName, double salary) {
        Employee newEmployee = new EmployeeImpl(employeeId, employeeName, salary);
        employeeHashMap.put(employeeId, newEmployee);
    }

    @Override
    public String showEmployee(int employeeId, AssignmentSupport assignmentSupport) throws NoSuchEmployeeException {
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
            return employeeString;
        } else {
            String errorString = String.format("Employee with ID %d does not exist!", employeeId);
            throw new NoSuchEmployeeException(errorString);
        }
    }

    @Override
    public void promoteEmployee(int employeeId, int promotionPercentage, AssignmentSupport assignmentSupport, DepartmentSupport departmentSupport) throws InsufficientFundsForPromotionException, PromotionPercentageNegativeException {
        if(promotionPercentage < 0){
            throw new PromotionPercentageNegativeException("The promotion percentage needs to be a positive floating number!");
        }
        Employee foundEmployee = employeeHashMap.get(employeeId);
        String department = assignmentSupport.getHiredEmployee(employeeId);
        double promotionSalary = foundEmployee.getEmployeeSalary() + (promotionPercentage / 100f) * foundEmployee.getEmployeeSalary();
        if (department.equals("N/A")) {
            foundEmployee.setEmployeeSalary(promotionSalary);
            employeeHashMap.put(employeeId, foundEmployee);
        } else {
            Department foundDepartment = departmentSupport.getSpecificDepartment(Integer.parseInt(department));
            if(promotionSalary > foundDepartment.getRemainingBudget()) {
                String error = String.format("Department %d’s budget does not allow for such a high promotion!", foundDepartment.getDepartmentId());
                throw new InsufficientFundsForPromotionException(error);
            } else {
                foundEmployee.setEmployeeSalary(promotionSalary);
                foundDepartment.updateBudget(promotionSalary);
                foundDepartment.updateBudget(foundDepartment.getYearlyBudget() - assignmentSupport.getEmployeesSalariesForBudgetUpdate(foundDepartment.getDepartmentId()));
            }
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return this.employeeHashMap.get(employeeId);
    }

    @Override
    public HashMap<Integer, Employee> getEmployees() {
        return this.employeeHashMap;
    }
}

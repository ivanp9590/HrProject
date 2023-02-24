package org.example.implementations;

import org.example.exceptions.InsufficientFundsException;
import org.example.interfaces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AssignmentSupportImpl implements AssignmentSupport {

    private HashMap<Integer, ArrayList<String>> hiredEmployees = new HashMap<>();
    private DepartmentSupport departmentSupport;
    private EmployeeSupport employeeSupport;

    public AssignmentSupportImpl(DepartmentSupport departmentSupport, EmployeeSupport employeeSupport) {
        this.departmentSupport = departmentSupport;
        this.employeeSupport = employeeSupport;
    }

    @Override
    public void assignEmployeeToDepartment(int employeeId, int departmentId) throws InsufficientFundsException {
        Department department = this.departmentSupport.getDepartments().get(departmentId);
        Employee employee = this.employeeSupport.getEmployees().get(employeeId);
        if(department.getRemainingBudget() >= employee.getEmployeeSalary()) {
            ArrayList<String> employees = new ArrayList<>();
            if(hiredEmployees.containsKey(departmentId)) {
                employees = hiredEmployees.get(departmentId);
                employees.add(Integer.toString(employeeId));
            } else {
                employees.add(Integer.toString(employeeId));
            }
            hiredEmployees.put(departmentId, employees);
            department.updateBudget(department.getYearlyBudget() - getEmployeesSalariesForBudgetUpdate(departmentId));
        } else {
            String errorMsg = String.format(
                "Unable to add employee %d to department %d as there is not enough budget!",
                employee.getEmployeeId(),
                department.getDepartmentId()
            );
            throw new InsufficientFundsException(errorMsg);
        }
    }

    @Override
    public double getEmployeesSalariesForBudgetUpdate(int departmentId) {
        double employeeSalaries = 0f;
        String hiredEmployees = this.getHiredEmployees(departmentId);
        ArrayList<Integer> employeeIds = Arrays.stream(hiredEmployees.split(", ")).map(Integer::valueOf).collect(Collectors.toCollection(ArrayList::new));
        for (Integer employeeId : employeeIds) {
           Employee employee = employeeSupport.getEmployeeById(employeeId);
           employeeSalaries += employee.getEmployeeSalary();
        }
        return employeeSalaries;
    }
    @Override
    public String getHiredEmployees(int departmentId) {
        String hiredEmployees = this.hiredEmployees.get(departmentId).stream().collect(Collectors.joining(", "));
        return hiredEmployees;
    }

    @Override
    public String getHiredEmployee(int employeeId) {
        String department = "N/A";
        for (Map.Entry<Integer, ArrayList<String>> entry: hiredEmployees.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<String> values = entry.getValue();
            if (values.contains(Integer.toString(employeeId))) {
                department = key.toString();
                return department;
            }
        }
        return department;
    }


}

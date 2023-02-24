package org.example.implementations;

import org.example.exceptions.InsufficientFundsException;
import org.example.interfaces.*;

import java.util.ArrayList;
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
            department.updateBudget(employee.getEmployeeSalary());
            ArrayList<String> employees = new ArrayList<>();
            if(hiredEmployees.containsKey(departmentId)) {
                employees = hiredEmployees.get(departmentId);
                employees.add(Integer.toString(employeeId));
            } else {
                employees.add(Integer.toString(employeeId));
            }
            hiredEmployees.put(departmentId, employees);
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

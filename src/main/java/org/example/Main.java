package org.example;

import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.InsufficientFundsForPromotionException;
import org.example.exceptions.NoSuchDepartmentException;
import org.example.exceptions.NoSuchEmployeeException;
import org.example.implementations.AssignmentSupportImpl;
import org.example.implementations.DepartmentSupportImpl;
import org.example.implementations.EmployeeSupportImpl;
import org.example.interfaces.AssignmentSupport;
import org.example.interfaces.DepartmentSupport;
import org.example.interfaces.EmployeeSupport;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Write a command: ");
        DepartmentSupport departmentSupport = new DepartmentSupportImpl();
        EmployeeSupport employeeSupport = new EmployeeSupportImpl();
        AssignmentSupport assignmentSupport = new AssignmentSupportImpl(departmentSupport, employeeSupport);
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();
            String[] commandDetails = command.split("\\s+");
            switch(commandDetails[0]) {
                case "CreateDepartment":
                    int departmentId = Integer.parseInt(commandDetails[1]);
                    String department = commandDetails[2];
                    int departmentBudget = Integer.parseInt(commandDetails[3]);
                    departmentSupport.createDepartment(departmentId, department, departmentBudget);
                    break;
                case "HireEmployee":
                    int employeeId = Integer.parseInt(commandDetails[1]);
                    String employeeName = commandDetails[2] + ' ' + commandDetails[3];
                    int employeeSalary = Integer.parseInt(commandDetails[4]);
                    employeeSupport.hireEmployee(employeeId, employeeName, employeeSalary);
                    break;
                case "AssignDepartment":
                    int employeeAssignmentId = Integer.parseInt(commandDetails[1]);
                    int departmentAssignmentId = Integer.parseInt(commandDetails[2]);
                    try {
                        assignmentSupport.assignEmployeeToDepartment(employeeAssignmentId, departmentAssignmentId);
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "PromoteEmployee":
                    int employeePromotionId = Integer.parseInt(commandDetails[1]);
                    double promotionPercentage = Double.parseDouble(commandDetails[2]);
                    try {
                        employeeSupport.promoteEmployee(employeePromotionId, promotionPercentage, assignmentSupport);
                    } catch (InsufficientFundsForPromotionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "ShowEmployee":
                    int employeeIdentifier = Integer.parseInt(commandDetails[1]);
                    try {
                        employeeSupport.showEmployee(employeeIdentifier, assignmentSupport);
                    } catch (NoSuchEmployeeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "ShowDepartment":
                    int departmentIdentifier = Integer.parseInt(commandDetails[1]);
                    try {
                        departmentSupport.showDepartment(departmentIdentifier, assignmentSupport);
                    } catch (NoSuchDepartmentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        } while(!command.equals("End"));
    }
}
package org.example;

import org.example.exceptions.*;
import org.example.implementations.AssignmentSupportImpl;
import org.example.implementations.DepartmentSupportImpl;
import org.example.implementations.EmployeeSupportImpl;
import org.example.interfaces.AssignmentSupport;
import org.example.interfaces.DepartmentSupport;
import org.example.interfaces.EmployeeSupport;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String INITIAL_COMMAND = "Write a command: ";
        long currentTimeInMilliSeconds = System.currentTimeMillis();
        Random random = new Random(currentTimeInMilliSeconds);
        int randomNumber = random.nextInt();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output"+randomNumber+".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(INITIAL_COMMAND);
        writeMethod(writer.append("------------------------------\n"+INITIAL_COMMAND));
        DepartmentSupport departmentSupport = new DepartmentSupportImpl();
        EmployeeSupport employeeSupport = new EmployeeSupportImpl();
        AssignmentSupport assignmentSupport = new AssignmentSupportImpl(departmentSupport, employeeSupport);
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();
            String[] commandDetails = command.split("\\s+");
            writeMethod(writer.append(command));
            switch(commandDetails[0]) {
                case "CreateDepartment":
                    int departmentId = Integer.parseInt(commandDetails[1]);
                    String department = commandDetails[2];
                    double departmentBudget = Double.parseDouble(commandDetails[3]);
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
                        writeMethod(writer.append(e.getMessage()));
                    }
                    break;
                case "PromoteEmployee":
                    int employeePromotionId = Integer.parseInt(commandDetails[1]);
                    int promotionPercentage = Integer.parseInt(commandDetails[2]);
                    try {
                        employeeSupport.promoteEmployee(employeePromotionId, promotionPercentage, assignmentSupport, departmentSupport);
                    } catch (InsufficientFundsForPromotionException | PromotionPercentageNegativeException e) {
                        System.out.println(e.getMessage());
                        writeMethod(writer.append(e.getMessage()));
                    }
                    break;
                case "ShowEmployee":
                    int employeeIdentifier = Integer.parseInt(commandDetails[1]);
                    try {
                        String employeeRes = employeeSupport.showEmployee(employeeIdentifier, assignmentSupport);
                        writeMethod(writer.append(employeeRes));
                    } catch (NoSuchEmployeeException e) {
                        System.out.println(e.getMessage());
                        writeMethod(writer.append(e.getMessage()));
                    }
                    break;
                case "ShowDepartment":
                    int departmentIdentifier = Integer.parseInt(commandDetails[1]);
                    try {
                        String departmentRes = departmentSupport.showDepartment(departmentIdentifier, assignmentSupport);
                        writeMethod(writer.append(departmentRes));
                    } catch (NoSuchDepartmentException e) {
                        System.out.println(e.getMessage());
                        writeMethod(writer.append(e.getMessage()));
                    }
                    break;
                case "UpdateDepartment":
                    int departmentUpdateIdentifier = Integer.parseInt(commandDetails[1]);
                    String departmentUpdateName = commandDetails[2];
                    int departmentUpdateBudget = Integer.parseInt(commandDetails[3]);
                    try {
                        departmentSupport.updateDepartment(departmentUpdateIdentifier, departmentUpdateName, departmentUpdateBudget);
                    } catch (DepartmentAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                        writeMethod(writer.append(e.getMessage()));
                    }
                    break;
            }
        } while(!command.equals("End"));
        writeMethod(writer.append("------------------------------"));
        writer.close();
    }

    private static void writeMethod(PrintWriter writer) {
        writer.append("\n");
    }
}
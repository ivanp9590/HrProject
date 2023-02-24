import org.example.implementations.EmployeeSupportImpl;
import org.example.interfaces.Employee;
import org.example.interfaces.EmployeeSupport;

import java.util.HashMap;

public class EmployeeSupportUnitTests {
    public void hireAndShowEmployee() {
        int EMPLOYEE_ID = 101;
        String EMPLOYEE_NAME = "Petko";
        double EMPLOYEE_SALARY = 100.0;
        EmployeeSupport employeeSupport = new EmployeeSupportImpl();
        employeeSupport.hireEmployee(EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_SALARY);
    }
}

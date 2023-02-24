import org.example.implementations.DepartmentImpl;
import org.example.implementations.EmployeeImpl;
import org.example.interfaces.Department;
import org.example.interfaces.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeUnitTests {
    @Test
    public void testEmployeeTest() {
        final int EMPLOYEE_ID = 100;
        final String EMPLOYEE_NAME = "Test Department";
        final double EMPLOYEE_SALARY = 10020.0;
        Employee employee = new EmployeeImpl(EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_SALARY);
        Assert.assertEquals("Employee ids match: ", EMPLOYEE_ID, employee.getEmployeeId());
        Assert.assertEquals("Employee names match: ", EMPLOYEE_NAME, employee.getEmployeeName());
        Assert.assertEquals("Employee salaries match: ", EMPLOYEE_SALARY, employee.getEmployeeSalary(), 0);
    }

    @Test
    public void setNewSalaryOfEmployeeTest() {
        final int EMPLOYEE_ID = 100;
        final String EMPLOYEE_NAME = "Test Department";
        final double EMPLOYEE_SALARY = 10020.0;
        final double EMPLOYEE_UPDATED_SALARY = 2100.0;
        Employee employee = new EmployeeImpl(EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_SALARY);
        employee.setEmployeeSalary(EMPLOYEE_UPDATED_SALARY);
        Assert.assertEquals("Employee updated salaries match: ", EMPLOYEE_UPDATED_SALARY, employee.getEmployeeSalary(), 0);
    }
}

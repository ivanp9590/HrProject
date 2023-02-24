import org.example.implementations.DepartmentImpl;
import org.example.interfaces.Department;
import org.junit.Assert;
import org.junit.Test;

public class DepartmentUnitTests {
    @Test
    public void testDepartmentInit() {
        final int DEPARTMENT_ID = 100;
        final String DEPARTMENT_NAME = "Test Department";
        final double DEPARTMENT_BUDGET = 1000000;
        Department department = new DepartmentImpl(DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_BUDGET);
        Assert.assertEquals("Department ids match: ", DEPARTMENT_ID, department.getDepartmentId());
        Assert.assertEquals("Department names match: ", DEPARTMENT_NAME, department.getDepartmentName());
        Assert.assertEquals("Department yearly budgets match: ", DEPARTMENT_BUDGET, department.getYearlyBudget(), 0);
        Assert.assertEquals("Department remaining budgets match: ", DEPARTMENT_BUDGET, department.getRemainingBudget(), 0);
    }

    @Test
    public void testUpdateDepartment() {
        final int DEPARTMENT_ID = 100;
        final String DEPARTMENT_NAME = "Test Department";
        final int DEPARTMENT_BUDGET = 1000000;
        final String DEPARTMENT_UPDATE_NAME = "Test Department Updated";
        final int DEPARTMENT_UPDATE_BUDGET = 2000000;
        Department department = new DepartmentImpl(DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_BUDGET);
        department.setDepartmentName(DEPARTMENT_UPDATE_NAME);
        department.setYearlyBudget(DEPARTMENT_UPDATE_BUDGET);
        Assert.assertEquals("Department updated names match: ", DEPARTMENT_UPDATE_NAME, department.getDepartmentName());
        Assert.assertEquals("Department updated yearly budgets match: ", DEPARTMENT_UPDATE_BUDGET, department.getYearlyBudget(), 0);
        Assert.assertEquals("Department updated remaining budgets match: ", DEPARTMENT_UPDATE_BUDGET, department.getRemainingBudget(), 0);
    }
}

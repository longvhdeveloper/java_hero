package my.java.vlong.practice1;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagement {

    private List<Employee> employees;

    public EmployeeManagement() {
        this.employees = new ArrayList<Employee>();
    }

    public void addEmployee(Employee employee) {
        if (employee != null) {
            this.employees.add(employee);
        }
    }

    public float calculateSumIncomeEmployee() {
        float sumIncome = 0;

        if (this.employees != null && this.employees.size() > 0) {
            for (Employee employee : employees) {
                if (employee == null) {
                    continue;
                }

                sumIncome += employee.calculateIncome();
            }
        }

        return sumIncome;
    }
}

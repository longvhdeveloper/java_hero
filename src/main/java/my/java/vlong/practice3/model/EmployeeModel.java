package my.java.vlong.practice3.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeModel implements IEmployee {
    private List<EmployeeEntity> employeeEntities;

    public EmployeeModel() {
        employeeEntities = new ArrayList<>();
    }

    @Override
    public boolean addEmployee(EmployeeEntity employeeEntity) {
        if (employeeEntity != null) {
            return employeeEntities.add(employeeEntity);
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(EmployeeEntity employeeEntity) {
        return false;
    }
}

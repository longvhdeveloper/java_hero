package my.java.vlong.homework2.infrastructure.repository;

import my.java.vlong.homework2.domain.entity.Employee;
import my.java.vlong.homework2.domain.exception.EmployeeException;
import my.java.vlong.homework2.domain.repository.IEmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements IEmployeeRepository {
    private List<Employee> employeeList;

    public EmployeeRepositoryImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public void addEmployee(Employee employee) throws EmployeeException {
        if (employee == null) {
            throw new EmployeeException("Employee to add is not valid.");
        }

        this.employeeList.add(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeList;
    }
}

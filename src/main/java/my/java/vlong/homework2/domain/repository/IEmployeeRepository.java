package my.java.vlong.homework2.domain.repository;

import my.java.vlong.homework2.domain.entity.Employee;
import my.java.vlong.homework2.domain.exception.EmployeeException;

import java.util.List;

public interface IEmployeeRepository {
    void addEmployee(Employee employee) throws EmployeeException;
    List<Employee> getEmployees();
}

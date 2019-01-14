package my.java.vlong.homework2.domain.service;

import my.java.vlong.homework2.domain.entity.*;
import my.java.vlong.homework2.domain.exception.EmployeeException;
import my.java.vlong.homework2.domain.repository.IEmployeeRepository;

import java.util.*;

public class EmployeeService {
    private IEmployeeRepository iEmployeeRepository;
    private Scanner scanner;

    public EmployeeService(IEmployeeRepository iEmployeeRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
        this.scanner = new Scanner(System.in);
    }

    public void addEmployee(MenuItem.EmployeeMenuItem employeeMenuItem) {
        switch (employeeMenuItem) {
            case WORKER:
                this.addWorker();
                break;
            case OFFICER:
                this.addOfficer();
                break;
            case MANAGER:
                this.addManager();
                break;
        }
    }

    public void displayEmployees() throws EmployeeException {
        List<Employee> employeeList = iEmployeeRepository.getEmployees();
        if (employeeList == null || employeeList.isEmpty()) {
            throw new EmployeeException("Employee list is empty or null");
        }

        employeeList.forEach((Employee employee) -> {
            displayEmployee(employee);
        });
    }

    public void findEmployeeHaveMaxSalaryBy(MenuItem.EmployeeFindConditionMenuItem employeeFindConditionMenuItem) {
        switch (employeeFindConditionMenuItem) {
            case FIND_BY_GENDER:
                this.findEmployeeHaveMaxSalaryByGender();
                break;
            case FIND_BY_POSITION:
                this.findEmployeeHaveMaxSalaryByPosition();
                break;
        }
    }

    private void addWorker() {
        try {
            System.out.println("Full name: ");
            String fullName = scanner.nextLine();
            System.out.println("Gender(1: Male, 0: Female, -1: Unknown): ");
            int genderCode = scanner.nextInt();
            Gender gender = Gender.valueOf(genderCode);

            System.out.println("Day of worker: ");
            int daysOfWorker = scanner.nextInt();
            System.out.println("Base Salary: ");
            float salary = scanner.nextFloat();

            // clear
            scanner.nextLine();

            Worker worker = new Worker(fullName, gender, salary, daysOfWorker);

            if (!worker.isValidData()) {
                System.out.println("Input data not valid. Please try again.");
                addWorker();
                return;
            }

            this.iEmployeeRepository.addEmployee(worker);
            System.out.println("Add worker success !");

        } catch (InputMismatchException e) {
            System.out.println("Input not valid.");
            scanner.reset();
            scanner.next();
            addWorker();
        } catch (EmployeeException e) {
            System.out.println("Can not add employee");
        }
    }

    private void addOfficer() {
        try {
            System.out.println("Full name: ");
            String fullName = scanner.nextLine();
            System.out.println("Gender(1: Male, 0: Female, -1: Unknown): ");
            int genderCode = scanner.nextInt();
            Gender gender = Gender.valueOf(genderCode);

            System.out.println("Weight: ");
            float weight = scanner.nextFloat();
            System.out.println("Base Salary: ");
            float salary = scanner.nextFloat();

            // clear
            scanner.nextLine();

            Officer officer = new Officer(fullName, gender, salary, weight);

            if (!officer.isValidData()) {
                System.out.println("Input data not valid. Please try again.");
                addOfficer();
                return;
            }

            this.iEmployeeRepository.addEmployee(officer);

            System.out.println("Add officer success !");
        } catch (InputMismatchException e) {
            System.out.println("Input not valid.");
            scanner.reset();
            scanner.next();
            addOfficer();
        } catch (EmployeeException e) {
            System.out.println("Can not add employee");
        }
    }

    private void addManager() {
        try {
            System.out.println("Full name: ");
            String fullName = scanner.nextLine();
            System.out.println("Gender(1: Male, 0: Female, -1: Unknown): ");
            int genderCode = scanner.nextInt();
            Gender gender = Gender.valueOf(genderCode);

            System.out.println("Number of employee: ");
            int numberOfEmployee = scanner.nextInt();
            System.out.println("Base Salary: ");
            float salary = scanner.nextFloat();

            // clear
            scanner.nextLine();

            Manager manager = new Manager(fullName, gender, salary, numberOfEmployee);

            if (!manager.isValidData()) {
                System.out.println("Input data not valid. Please try again.");
                addManager();
                return;
            }

            this.iEmployeeRepository.addEmployee(manager);

            System.out.println("Add manager success !");
        } catch (InputMismatchException e) {
            System.out.println("Input not valid.");
            scanner.reset();
            scanner.next();
            addManager();
        } catch (EmployeeException e) {
            System.out.println("Can not add employee");
        }
    }

    private void displayEmployee(Employee employee) {
        if (employee != null) {
            System.out.println("========================");
            if (employee instanceof Worker) {
                ((Worker) employee).displayInfo();
            } else if (employee instanceof Officer) {
                ((Officer) employee).displayInfo();
            } else if (employee instanceof Manager) {
                ((Manager) employee).displayInfo();
            }
            System.out.println("========================");
        }
    }

    private void findEmployeeHaveMaxSalaryByGender() {
        List<Employee> employeeList = this.iEmployeeRepository.getEmployees();
        if (employeeList == null || employeeList.size() == 0) {
            return;
        }

        List<Employee> results = new ArrayList<>();

        Arrays.asList(Gender.values()).forEach((Gender gender) -> {
            Optional<Employee> optional =
                    employeeList.stream().filter(employee -> employee != null && employee.getGender().equals(gender)).max(Comparator.comparing(Employee::calculateSalary));
            optional.ifPresent(results::add);
        });

//                for (Gender gender : Gender.values()) {
//                    Employee employeeMax = null;
//                    for (Employee employee : employeeList) {
//                        if (employee == null) {
//                            continue;
//                        }
//        
//                        if (employee.getGender().equals(gender)) {
//        
//                            if (employeeMax == null) {
//                                employeeMax = employee;
//                            } else {
//                                if (employeeMax.calculateSalary() < employee.calculateSalary()) {
//                                    employeeMax = employee;
//                                }
//                            }
//                        }
//                    }
//                    results.add(employeeMax);
//                }

        if (results.size() > 0) {
            results.forEach(this::displayEmployee);
        }
    }

    private void findEmployeeHaveMaxSalaryByPosition() {
        List<Employee> employeeList = this.iEmployeeRepository.getEmployees();
        if (employeeList == null || employeeList.size() == 0) {
            return;
        }

        List<Employee> results = new ArrayList<>();

        Arrays.asList(Position.values()).forEach((Position position) -> {
            Optional<Employee> optional =
                    employeeList.stream().filter(employee -> employee != null && employee.getPosition().equals(position)).max(Comparator.comparing(Employee::calculateSalary));
            optional.ifPresent(results::add);
        });

        //        for (Position position : Position.values()) {
        //            Employee employeeMax = null;
        //            for (Employee employee : employeeList) {
        //                if (employee == null) {
        //                    continue;
        //                }
        //
        //                if (employee.getPosition().equals(position)) {
        //
        //                    if (employeeMax == null) {
        //                        employeeMax = employee;
        //                    } else {
        //                        if (employeeMax.calculateSalary() < employee.calculateSalary()) {
        //                            employeeMax = employee;
        //                        }
        //                    }
        //                }
        //            }
        //            results.add(employeeMax);
        //        }

        if (results.size() > 0) {
            results.forEach(this::displayEmployee);
        }
    }
}

package my.java.vlong.homework2.domain.service;

import my.java.vlong.homework2.domain.entity.MenuItem;
import my.java.vlong.homework2.domain.exception.EmployeeException;
import my.java.vlong.homework2.domain.repository.IMenuRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuService {
    private IMenuRepository iMenuRepository;
    private Scanner scanner;
    private EmployeeService employeeService;

    public MenuService(IMenuRepository iMenuRepository, EmployeeService employeeService) {
        this.iMenuRepository = iMenuRepository;
        this.employeeService = employeeService;
        this.scanner = new Scanner(System.in);
    }

    public void executeMenu() {
        try {
            int choice = 0;
            MenuItem itemChoice;
            do {

                iMenuRepository.displayMenu();
                choice = scanner.nextInt();
                itemChoice = MenuItem.valueOf(choice);

                switch (itemChoice) {
                    case ENTRY_EMPLOYEES:
                        addEmployee();
                        break;
                    case DISPLAY_EMPLOYEES:
                        displayEmployees();
                        break;
                    case FIND_EMPLOYEE_BY_SALARY:
                        findEmployeeByMaxSalary();
                        break;
                    case EXIT:
                        this.exit();
                }

                this.executeMenu();
            } while (!MenuItem.valueIsValid(itemChoice));
        } catch (InputMismatchException ex) {
            System.out.println("Input value is not valid. Please try again.");
            // Clear scanner to can get again input data
            scanner.reset();
            scanner.next();

            this.executeMenu();
        }

        scanner.close();
    }

    private void addEmployee() {
        try {
            iMenuRepository.displayAddEmployeeMenu();
            int choice = 0;
            MenuItem.EmployeeMenuItem employeeMenuItemChoice;

            do {
                choice = scanner.nextInt();
                employeeMenuItemChoice = MenuItem.EmployeeMenuItem.valueOf(choice);

                if (employeeMenuItemChoice.equals(MenuItem.EmployeeMenuItem.RETURN_MAIN)) {
                    this.executeMenu();
                    return;
                }
                employeeService.addEmployee(employeeMenuItemChoice);

            } while (!MenuItem.EmployeeMenuItem.valueIsValid(employeeMenuItemChoice));
        } catch (InputMismatchException e) {
            scanner.reset();
            scanner.next();
        }
    }

    private void displayEmployees() {
        try {
            employeeService.displayEmployees();
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findEmployeeByMaxSalary() {
        try {
            iMenuRepository.displayMenuFindEmployeeByCondition();
            int choice = 0;
            MenuItem.EmployeeFindConditionMenuItem employeeFindConditionMenuItem;

            do {
                choice = scanner.nextInt();
                employeeFindConditionMenuItem = MenuItem.EmployeeFindConditionMenuItem.valueOf(choice);

                if (employeeFindConditionMenuItem.equals(MenuItem.EmployeeFindConditionMenuItem.RETURN_MAIN)) {
                    this.executeMenu();
                    return;
                }

                employeeService.findEmployeeHaveMaxSalaryBy(employeeFindConditionMenuItem);
            } while (!MenuItem.EmployeeFindConditionMenuItem.valueIsValid(employeeFindConditionMenuItem));
        } catch (InputMismatchException e) {
            scanner.reset();
            scanner.next();
        }
    }

    private void exit() {
        System.exit(0);
    }
}

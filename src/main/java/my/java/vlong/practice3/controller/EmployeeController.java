package my.java.vlong.practice3.controller;

import my.java.vlong.practice3.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EmployeeController {
    private Scanner scanner;
    private EmployeeModel employeeModel;

    public EmployeeController() {
        scanner = new Scanner(System.in);
        employeeModel = new EmployeeModel();
    }

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.execute();
    }

    private void execute() {
        int choice = 0;
        while (true) {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    System.exit(0);
            }
            this.execute();
        }
    }

    private void addEmployee() {
        System.out.println("Choose type of employee to add");
        int choice = 0;
        EmployeeEntity employeeEntity = null;
        do {
            displayEmployeeTypeMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    employeeEntity = new WorkerEntity();
                    break;
                case 2:
                    employeeEntity = new ManagerEntity();
                    break;
                case 3:
                    employeeEntity = new DirectorEntity();
                    break;
                case 4:
                    this.execute();
                    break;
            }

        } while (employeeEntity == null);

        // clear
        scanner.nextLine();

        System.out.println("Enter full name: ");
        String fullName = scanner.nextLine();

        System.out.println("Enter date of birth(dd/MM/yyyy): ");
        String dateOfBirthString = scanner.nextLine();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            simpleDateFormat.parse(dateOfBirthString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayMenu() {
        System.out.println("==== Employee Management ====");
        System.out.println("1. Add employee");
        System.out.println("2. Display list employee");
        System.out.println("3. Delete employee");
        System.out.println("4. Calculate salary employee");
        System.out.println("5. Find employee have max salary");
        System.out.println("6. Find employee have salary large than 9.000.000");
        System.out.println("7. Sort employee by date of birth");
        System.out.println("8. Save list employee to file");
        System.out.println("9. Exit");
        System.out.println("Enter your choice");
    }

    private void displayEmployeeTypeMenu() {
        System.out.println("1. Worker");
        System.out.println("2. Manager");
        System.out.println("3. Director");
        System.out.println("4. Return main");
        System.out.println("Enter your choice");
    }
}

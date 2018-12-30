package my.java.vlong.homework2.infrastructure.repository;

import my.java.vlong.homework2.domain.repository.IMenuRepository;

public class MenuRepositoryImpl implements IMenuRepository {
    @Override
    public void displayMenu() {
        System.out.println("=== Product Management System ===");
        System.out.println("1. Entry employee info.");
        System.out.println("2. Display employee info.");
        System.out.println("3. Find employee info by max salary.");
        System.out.println("4. Exit");
        System.out.println("=================================");
        System.out.println("Enter your choice: ");
    }

    @Override
    public void displayMenuFindEmployeeByCondition() {
        System.out.println("Choose condition to find: ");
        System.out.println("1. Gender");
        System.out.println("2. Position");
        System.out.println("Enter your choice: ");
    }

    @Override
    public void displayAddEmployeeMenu() {
        System.out.println("Choose type employee to entry: ");
        System.out.println("1. Worker");
        System.out.println("2. Officer");
        System.out.println("3. Manager");
        System.out.println("4. Return main menu");
        System.out.println("Enter your choice: ");
    }
}

package my.java.vlong.homework3.controller;

public class MenuManagement {

    public void displayMenu() {
        System.out.println("===== Management =====");
        System.out.println("1. StudentEntity");
        System.out.println("2. CourseEntity");
        System.out.println("3. Exit");
        System.out.println("Enter your choice: ");
    }

    public void displayCourseMenu() {
        System.out.println("===== CourseEntity =====");
        System.out.println("1. Add CourseEntity");
        System.out.println("2. Update CourseEntity");
        System.out.println("3. Delete CourseEntity");
        System.out.println("4. Search CourseEntity");
        System.out.println("5. View all CourseEntity");
        System.out.println("6. View StudentEntity in CourseEntity");
        System.out.println("7. Return main menu");
        System.out.println("Enter your choice: ");
    }

    public void displayStudentMenu() {
        System.out.println("===== StudentEntity =====");
        System.out.println("1. Add StudentEntity");
        System.out.println("2. Update StudentEntity");
        System.out.println("3. Delete StudentEntity");
        System.out.println("4. Search StudentEntity");
        System.out.println("5. View all StudentEntity");
        System.out.println("6. Return main menu");
        System.out.println("Enter your choice: ");
    }
}

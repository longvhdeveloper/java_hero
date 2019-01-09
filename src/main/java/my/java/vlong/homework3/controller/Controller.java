package my.java.vlong.homework3.controller;

import java.util.Scanner;

public class Controller {

    private CourseController courseController;
    private StudentController studentController;
    private Scanner scanner;


    public Controller() {
        courseController = new CourseController();
        studentController = new StudentController();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.execute();
    }

    private void execute() {
        int choice = 0;
        Menu menu = null;
        while (true) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            menu = Menu.valueOf(choice);

            if (menu == null) {
                continue;
            }

            switch (menu) {
                case MAIN_STUDENT:
                    this.executeStudent();
                    break;
                case MAIN_COURSE:
                    this.executeCourse();
                    break;
                case EXIT:
                    exit();
            }

            this.execute();
        }
    }

    private void executeCourse() {
        int choice = 0;
        Menu.MenuCourse menuCourse = null;
        while (true) {
            displayCourseMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            menuCourse = Menu.MenuCourse.valueOf(choice);

            if (menuCourse == null) {
                continue;
            }

            switch (menuCourse) {
                case ADD:
                    courseController.addCourse();
                    break;
                case UPDATE:
                    courseController.updateCourse();
                    break;
                case DELETE:
                    courseController.deleteCourse();
                    break;
                case SEARCH:
                    courseController.searchCourse();
                    break;
                case VIEW_ALL:
                    courseController.viewAllCourse();
                    break;
                case VIEW_STUDENT_OF_COURSE:
                    courseController.viewStudentInCourse();
                    break;
            }

            if (menuCourse.equals(Menu.MenuCourse.RETURN_MAIN)) {
                this.execute();
                return;
            }

            this.executeCourse();
        }
    }

    private void executeStudent() {
        int choice = 0;
        Menu.MenuStudent menuStudent = null;
        while (true) {
            displayCourseMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            menuStudent = Menu.MenuStudent.valueOf(choice);

            if (menuStudent == null) {
                continue;
            }

            switch (menuStudent) {
                case ADD:
                    studentController.addStudent();
                    break;
                case UPDATE:
                    studentController.updateStudent();
                    break;
                case DELETE:
                    studentController.deleteStudent();
                    break;
                case SEARCH:
                    studentController.searchStudent();
                    break;
                case VIEW_ALL:
                    studentController.viewAllStudent();
                    break;

            }

            if (menuStudent.equals(Menu.MenuStudent.RETURN_MAIN)) {
                this.execute();
                return;
            }

            this.executeCourse();
        }
    }

    private void displayMenu() {
        System.out.println("===== Management =====");
        System.out.println("1. StudentEntity");
        System.out.println("2. CourseEntity");
        System.out.println("3. Exit");
        System.out.println("Enter your choice: ");
    }

    private void displayCourseMenu() {
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

    private void displayStudentMenu() {
        System.out.println("===== StudentEntity =====");
        System.out.println("1. Add StudentEntity");
        System.out.println("2. Update StudentEntity");
        System.out.println("3. Delete StudentEntity");
        System.out.println("4. Search StudentEntity");
        System.out.println("5. View all StudentEntity");
        System.out.println("6. Return main menu");
        System.out.println("Enter your choice: ");
    }

    private void exit() {
        System.exit(0);
    }
}

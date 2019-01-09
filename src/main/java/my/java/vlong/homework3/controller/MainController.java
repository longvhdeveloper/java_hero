package my.java.vlong.homework3.controller;

import java.util.Scanner;

public class MainController {

    private CourseController courseController;
    private StudentController studentController;
    private MenuManagement menuManagement;
    private Scanner scanner;


    public MainController() {
        courseController = new CourseController();
        studentController = new StudentController();
        menuManagement = new MenuManagement();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.execute();
    }

    private void execute() {
        int choice = 0;
        Menu menu = null;
        while (true) {
            menuManagement.displayMenu();
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
            menuManagement.displayCourseMenu();
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
            menuManagement.displayCourseMenu();
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


    private void exit() {
        System.exit(0);
    }
}

package my.java.vlong.homework3_refactor.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import my.java.vlong.homework3_refactor.controller.CourseController;
import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Course;

public class CourseView {

    private Scanner scanner;
    private CourseController courseController;
    private Map<String, Object> response;

    public CourseView() {
        scanner = new Scanner(System.in);
        courseController = new CourseController();
    }

    public static void main(String[] args) {
        CourseView courseView = new CourseView();
        courseView.execute();
    }

    private void execute() {
        int choice = 0;
        while (true) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    updateCourse();
                    break;
                case 3:
                    deleteCourse();
                    break;
                case 4:
                    searchCourse();
                    break;
                case 5:
                    viewAllCourse();
                    break;
                case 6:
                    viewStudentInCourse();
                    break;
            }

            if (choice == 7) {
                break;
            }

            this.execute();
        }
    }

    private void addCourse() {
        System.out.println("Enter course name: ");
        String courseName = scanner.nextLine();

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(courseName);

        response = courseController.add(courseDTO);

        if (response.containsKey("success") && response.get("success").equals("1")) {
            System.out.println(response.getOrDefault("message", "Success"));
            if (response.containsKey("course")) {
                System.out.println("Information of course added: ");
                CourseDTO courseAdded = (CourseDTO) response.get("course");
                System.out.println(courseAdded);
            }
        } else {
            System.out.println(response.getOrDefault("error", "Error"));
        }
    }

    private void updateCourse() {
        System.out.println("Enter ID of course you want update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new course name: ");
        String courseName = scanner.nextLine();

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(courseName);

        response = courseController.update(id, courseDTO);

        if (response.containsKey("success") && response.get("success").equals("1")) {
            System.out.println(response.getOrDefault("message", "Success"));
            if (response.containsKey("course")) {
                System.out.println("Information of course updated: ");
                CourseDTO courseUpdated = (CourseDTO) response.get("course");
                System.out.println(courseUpdated);
            }
        } else {
            System.out.println(response.getOrDefault("error", "Error"));
        }
    }

    private void deleteCourse() {
        System.out.println("Enter id of courseEntity to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        response = courseController.delete(id);
        if (response.containsKey("success") && response.get("success").equals("1")) {
            System.out.println(response.getOrDefault("message", "Success"));
        } else {
            System.out.println(response.getOrDefault("error", "Error"));
        }
    }

    private void viewAllCourse() {
        response = courseController.findAll();
        if (response.containsKey("courses")) {
            List<CourseDTO> courses = (List<CourseDTO>) response.get("courses");
            courses.forEach(System.out::println);
        } else {
            System.out.println(response.getOrDefault("error", "Error"));
        }
    }

    private void searchCourse() {
        System.out.println("Enter name of course: ");
        String keyWord = scanner.nextLine();

        response = courseController.search(keyWord);
        if (response.containsKey("courses")) {
            List<CourseDTO> courses = (List<CourseDTO>) response.get("courses");
            courses.forEach(System.out::println);
        } else {
            System.out.println(response.getOrDefault("error", "Error"));
        }
    }

    private void displayMenu() {
        System.out.println("===== Course =====");
        System.out.println("1. Add Course");
        System.out.println("2. Update Course");
        System.out.println("3. Delete Course");
        System.out.println("4. Search Course");
        System.out.println("5. View all Course");
        System.out.println("6. View Student in Course");
        System.out.println("7. Return main menu");
        System.out.println("Enter your choice: ");
    }

    private void viewStudentInCourse() {
        System.out.println("Enter id of course: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        response = courseController.viewStudentInCourse(id);
        if (response.containsKey("students")) {
            List<StudentDTO> students = (List<StudentDTO>) response.get("students");
            students.forEach(System.out::println);
        } else {
            System.out.println(response.getOrDefault("error", "Error"));
        }
    }
}

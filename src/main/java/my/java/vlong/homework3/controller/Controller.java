package my.java.vlong.homework3.controller;

import my.java.vlong.homework3.model.Course;
import my.java.vlong.homework3.model.Gender;
import my.java.vlong.homework3.model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Controller {

    private Scanner scanner;
    private CourseManagement courseManagement;
    private StudentManagement studentManagement;

    public Controller() {
        this.scanner = new Scanner(System.in);
        this.courseManagement = new CourseManagement();
        this.studentManagement = new StudentManagement();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.execute();
    }

    private void execute() {
        int choice = 0;
        do {
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
                    searchCourse();
                    break;
                case 4:
                    viewAllCourse();
                    break;
                case 5:
                    deleteCourse();
                    break;
                case 6:
                    viewStudentInCourse();
                    break;
                case 7:
                    addStudent();
                    break;
                case 8:
                    updateStudent();
                    break;
                case 9:
                    searchStudent();
                    break;
                case 10:
                    viewAllStudent();
                    break;
                case 11:
                    deleteStudent();
                    break;
            }

            if (choice == 12) {
                break;
            }

            this.execute();
        } while (choice < 1 || choice > 12);
    }

    private void displayMenu() {
        System.out.println("===== Management =====");
        System.out.println("1. Add Course");
        System.out.println("2. Update Course");
        System.out.println("3. Search Course");
        System.out.println("4. View all Course");
        System.out.println("5. Delete Course");
        System.out.println("6. View Student in Course");
        System.out.println("7. Add student");
        System.out.println("8. Update student");
        System.out.println("9. Search Student");
        System.out.println("10. View all Student");
        System.out.println("11. Delete Student");

        System.out.println("12. Exit");
        System.out.println("Enter your choice: ");
    }

    private void addCourse() {
        System.out.println("Enter course name: ");
        String courseName = scanner.nextLine();

        Course course = new Course(courseName);
        if (course.isValid()) {
            if (courseManagement.addCourse(course)) {
                System.out.println("Add course ok");
            } else {
                System.out.println("Add course is error");
            }
        } else {
            System.out.println("Input value not valid. Please try again");
        }
    }

    private void updateCourse() {
        System.out.println("Enter ID of course you want update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Course course = courseManagement.getCourse(id);
        if (course == null) {
            System.out.println("Course you want update not found");
            return;
        }

        System.out.println(course.toString());

        System.out.println("Enter new course name: ");
        String courseName = scanner.nextLine();
        course.setName(courseName);

        if (course.isValid()) {
            if (courseManagement.updateCourse(course)) {
                System.out.println("Update course ok");
            } else {
                System.out.println("Update course is error");
            }
        } else {
            System.out.println("Input value not valid. Please try again");
        }
    }

    private void searchCourse() {
        System.out.println("Enter id or name of course: ");
        String keyword = scanner.nextLine();
        List<Course> courses = courseManagement.search(keyword);
        if (courses == null || courses.size() == 0) {
            System.out.println("Result not found.");
            return;
        }
        System.out.println("Search result:");
        courses.forEach(System.out::println);
    }

    private void viewAllCourse() {
        List<Course> courses = courseManagement.getCourses();
        if (courses.size() == 0) {
            System.out.println("No have course.");
            return;
        }

        System.out.println("Course List:");
        courses.forEach(System.out::println);
    }

    private void deleteCourse() {
        System.out.println("Enter id of course to delete: ");
        int id = scanner.nextInt();
        Course course = courseManagement.getCourse(id);
        if (course == null) {
            System.out.println("Course you want update not found");
            return;
        }

        List<Student> students = studentManagement.getStudentByCourse(course);
        if (courseManagement.deleteCourse(course)) {
            // update all course_id of student that course is deleted to 0
            students.forEach(student -> {
                student.setCourse(new Course(0, ""));
                studentManagement.updateStudent(student);
            });

            System.out.println("Delete course success.");
        } else {
            System.out.println("Delete course error.");
        }
    }

    private void addStudent() {
        try {
            System.out.println("Enter student name: ");
            String studentName = scanner.nextLine();
            System.out.println("Enter date of birth (dd/MM/yyyy): ");
            String dateOfBirthString = scanner.nextLine();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = simpleDateFormat.parse(dateOfBirthString);
            System.out.println("Enter gender (1: Male, 2: Female, -1: Unknown): ");
            int genderCode = scanner.nextInt();
            scanner.nextLine();
            Gender gender = Gender.valueOf(genderCode);
            System.out.println("Enter course id :");
            int courseId = scanner.nextInt();
            Course course = courseManagement.getCourse(courseId);

            Student student = new Student(studentName, dateOfBirth, gender, course);
            if (student.isValid()) {
                if (studentManagement.addStudent(student)) {
                    System.out.println("Add student success");
                } else {
                    System.out.println("Add student error");
                }
            } else {
                System.out.println("Input value not valid. Please try again");
            }

        } catch (ParseException e) {
            System.out.println("Format of Date of birth is not valid");
        }
    }

    private void updateStudent() {
        System.out.println("Enter ID of student you want update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentManagement.getStudent(id);
        if (student == null) {
            System.out.println("Course you want update not found");
            return;
        }

        System.out.println(student.toString());

        try {
            System.out.println("Enter student name: ");
            String studentName = scanner.nextLine();
            student.setFullName(studentName);

            System.out.println("Enter date of birth (dd/MM/yyyy): ");
            String dateOfBirthString = scanner.nextLine();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateOfBirth = simpleDateFormat.parse(dateOfBirthString);
            student.setDateOfBirth(dateOfBirth);

            System.out.println("Enter gender (1: Male, 2: Female, -1: Unknown): ");
            int genderCode = scanner.nextInt();
            scanner.nextLine();
            Gender gender = Gender.valueOf(genderCode);
            student.setGender(gender);

            System.out.println("Enter course id :");
            int courseId = scanner.nextInt();
            Course course = courseManagement.getCourse(courseId);
            student.setCourse(course);

            if (student.isValid()) {
                if (studentManagement.updateStudent(student)) {
                    System.out.println("Update student success");
                } else {
                    System.out.println("Update student error");
                }
            } else {
                System.out.println("Input value not valid. Please try again");
            }

        } catch (ParseException e) {
            System.out.println("Format of Date of birth is not valid");
        }
    }

    private void searchStudent() {
        System.out.println("Enter id or name of student: ");
        String keyword = scanner.nextLine();
        List<Student> students = studentManagement.search(keyword);
        if (students == null || students.size() == 0) {
            System.out.println("Result not found.");
            return;
        }
        System.out.println("Search result:");
        students.forEach(System.out::println);
    }

    private void viewAllStudent() {
        List<Student> students = studentManagement.getStudents();
        if (students.size() == 0) {
            System.out.println("No have students.");
            return;
        }

        System.out.println("Students List:");
        students.forEach(System.out::println);
    }

    private void deleteStudent() {
        System.out.println("Enter id of student to delete: ");
        int id = scanner.nextInt();
        Student student = studentManagement.getStudent(id);
        if (student == null) {
            System.out.println("Course you want update not found");
            return;
        }

        if (studentManagement.deleteStudent(student)) {
            System.out.println("Delete course success.");
        } else {
            System.out.println("Delete course error.");
        }
    }

    private void viewStudentInCourse() {
        System.out.println("Enter id of course: ");
        int id = scanner.nextInt();
        Course course = courseManagement.getCourse(id);
        if (course == null) {
            System.out.println("Course not found");
            return;
        }
        List<Student> students = studentManagement.getStudentByCourse(course);
        if (students == null || students.size() == 0) {
            System.out.println("Result not found.");
            return;
        }
        System.out.println("Search result:");
        students.forEach(System.out::println);
    }
}

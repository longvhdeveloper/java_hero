package my.java.vlong.homework3.controller;

import my.java.vlong.homework3.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class StudentController {

    private CourseModel courseModel;
    private StudentModel studentModel;
    private Scanner scanner;

    public StudentController() {
        courseModel = new CourseModel();
        studentModel = new StudentModel();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        try {
            System.out.println("Enter studentEntity name: ");
            String studentName = scanner.nextLine();
            System.out.println("Enter date of birth (dd/MM/yyyy): ");
            String dateOfBirthString = scanner.nextLine();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dateOfBirth = simpleDateFormat.parse(dateOfBirthString);
            System.out.println("Enter gender (1: Male, 2: Female, -1: Unknown): ");
            int genderCode = scanner.nextInt();
            scanner.nextLine();
            Gender gender = Gender.valueOf(genderCode);
            System.out.println("Enter courseEntity id :");
            int courseId = scanner.nextInt();
            CourseEntity courseEntity = courseModel.getCourse(courseId);

            StudentEntity studentEntity = new StudentEntity(studentName, dateOfBirth, gender, courseEntity);
            if (studentEntity.isValid()) {
                if (studentModel.addStudent(studentEntity)) {
                    System.out.println("Add studentEntity success");
                } else {
                    System.out.println("Add studentEntity error");
                }
            } else {
                System.out.println("Input value not valid. Please try again");
            }

        } catch (ParseException e) {
            System.out.println("Format of Date of birth is not valid");
        }
    }

    public void updateStudent() {
        System.out.println("Enter ID of studentEntity you want update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        StudentEntity studentEntity = studentModel.getStudent(id);
        if (studentEntity == null) {
            System.out.println("CourseEntity you want update not found");
            return;
        }

        System.out.println(studentEntity.toString());

        try {
            System.out.println("Enter studentEntity name: ");
            String studentName = scanner.nextLine();
            studentEntity.setFullName(studentName);

            System.out.println("Enter date of birth (dd/MM/yyyy): ");
            String dateOfBirthString = scanner.nextLine();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dateOfBirth = simpleDateFormat.parse(dateOfBirthString);
            studentEntity.setDateOfBirth(dateOfBirth);

            System.out.println("Enter gender (1: Male, 2: Female, -1: Unknown): ");
            int genderCode = scanner.nextInt();
            scanner.nextLine();
            Gender gender = Gender.valueOf(genderCode);
            studentEntity.setGender(gender);

            System.out.println("Enter courseEntity id :");
            int courseId = scanner.nextInt();
            CourseEntity courseEntity = courseModel.getCourse(courseId);
            studentEntity.setCourseEntity(courseEntity);

            if (studentEntity.isValid()) {
                if (studentModel.updateStudent(studentEntity)) {
                    System.out.println("Update studentEntity success");
                } else {
                    System.out.println("Update studentEntity error");
                }
            } else {
                System.out.println("Input value not valid. Please try again");
            }

        } catch (ParseException e) {
            System.out.println("Format of Date of birth is not valid");
        }
    }

    public void searchStudent() {
        System.out.println("Enter id or name of student: ");
        String keyword = scanner.nextLine();
        List<StudentEntity> studentEntities = studentModel.search(keyword);
        if (studentEntities == null || studentEntities.size() == 0) {
            System.out.println("Result not found.");
            return;
        }
        System.out.println("Search result:");
        studentEntities.forEach(System.out::println);
    }

    public void viewAllStudent() {
        List<StudentEntity> studentEntities = studentModel.getStudents();
        if (studentEntities.size() == 0) {
            System.out.println("No have studentEntities.");
            return;
        }

        System.out.println("Students List:");
        studentEntities.forEach(System.out::println);
    }

    public void deleteStudent() {
        System.out.println("Enter id of studentEntity to delete: ");
        int id = scanner.nextInt();
        StudentEntity studentEntity = studentModel.getStudent(id);
        if (studentEntity == null) {
            System.out.println("CourseEntity you want update not found");
            return;
        }

        if (studentModel.deleteStudent(studentEntity)) {
            System.out.println("Delete course success.");
        } else {
            System.out.println("Delete course error.");
        }
    }
}

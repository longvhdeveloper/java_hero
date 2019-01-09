package my.java.vlong.homework3.controller;

import my.java.vlong.homework3.model.CourseEntity;
import my.java.vlong.homework3.model.CourseModel;
import my.java.vlong.homework3.model.StudentEntity;
import my.java.vlong.homework3.model.StudentModel;

import java.util.List;
import java.util.Scanner;

public class CourseController {

    private CourseModel courseModel;
    private StudentModel studentManagement;
    private Scanner scanner;

    public CourseController() {
        courseModel = new CourseModel();
        studentManagement = new StudentModel();
        scanner = new Scanner(System.in);
    }

    public void addCourse() {
        System.out.println("Enter courseEntity name: ");
        String courseName = scanner.nextLine();

        CourseEntity courseEntity = new CourseEntity(courseName);
        if (courseEntity.isValid()) {
            if (courseModel.addCourse(courseEntity)) {
                System.out.println("Add courseEntity ok");
            } else {
                System.out.println("Add courseEntity is error");
            }
        } else {
            System.out.println("Input value not valid. Please try again");
        }
    }

    public void updateCourse() {
        System.out.println("Enter ID of courseEntity you want update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        CourseEntity courseEntity = courseModel.getCourse(id);
        if (courseEntity == null) {
            System.out.println("CourseEntity you want update not found");
            return;
        }

        System.out.println(courseEntity.toString());

        System.out.println("Enter new courseEntity name: ");
        String courseName = scanner.nextLine();
        courseEntity.setName(courseName);

        if (courseEntity.isValid()) {
            if (courseModel.updateCourse(courseEntity)) {
                System.out.println("Update courseEntity ok");
            } else {
                System.out.println("Update courseEntity is error");
            }
        } else {
            System.out.println("Input value not valid. Please try again");
        }
    }

    public void searchCourse() {
        System.out.println("Enter id or name of course: ");
        String keyword = scanner.nextLine();
        List<CourseEntity> cours = courseModel.search(keyword);
        if (cours == null || cours.size() == 0) {
            System.out.println("Result not found.");
            return;
        }
        System.out.println("Search result:");
        cours.forEach(System.out::println);
    }

    public void viewAllCourse() {
        List<CourseEntity> cours = courseModel.getCourses();
        if (cours.size() == 0) {
            System.out.println("No have course.");
            return;
        }

        System.out.println("CourseEntity List:");
        cours.forEach(System.out::println);
    }

    public void deleteCourse() {
        System.out.println("Enter id of courseEntity to delete: ");
        int id = scanner.nextInt();
        CourseEntity courseEntity = courseModel.getCourse(id);
        if (courseEntity == null) {
            System.out.println("CourseEntity you want update not found");
            return;
        }

        List<StudentEntity> studentEntities = studentManagement.getStudentByCourse(courseEntity);
        if (courseModel.deleteCourse(courseEntity)) {
            // update all course_id of student that courseEntity is deleted to 0
            studentEntities.forEach(studentEntity -> {
                studentEntity.setCourseEntity(new CourseEntity(0, ""));
                studentManagement.updateStudent(studentEntity);
            });

            System.out.println("Delete courseEntity success.");
        } else {
            System.out.println("Delete courseEntity error.");
        }
    }

    public void viewStudentInCourse() {
        System.out.println("Enter id of courseEntity: ");
        int id = scanner.nextInt();
        CourseEntity courseEntity = courseModel.getCourse(id);
        if (courseEntity == null) {
            System.out.println("CourseEntity not found");
            return;
        }
        List<StudentEntity> studentEntities = studentManagement.getStudentByCourse(courseEntity);
        if (studentEntities == null || studentEntities.size() == 0) {
            System.out.println("Result not found.");
            return;
        }
        System.out.println("Search result:");
        studentEntities.forEach(System.out::println);
    }
}

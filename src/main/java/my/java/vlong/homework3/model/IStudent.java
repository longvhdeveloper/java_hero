package my.java.vlong.homework3.model;

import java.util.List;

public interface IStudent {
    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    Student getStudent(int id);

    List<Student> search(String keyword);

    List<Student> getStudents();

    List<Student> getStudentByCourse(Course course);

    boolean deleteStudent(Student student);
}

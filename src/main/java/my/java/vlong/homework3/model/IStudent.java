package my.java.vlong.homework3.model;

import java.util.List;

public interface IStudent {
    boolean addStudent(StudentEntity studentEntity);

    boolean updateStudent(StudentEntity studentEntity);

    boolean deleteStudent(StudentEntity studentEntity);

    StudentEntity getStudent(int id);

    List<StudentEntity> search(String keyword);

    List<StudentEntity> getStudents();

    List<StudentEntity> getStudentByCourse(CourseEntity courseEntity);
}

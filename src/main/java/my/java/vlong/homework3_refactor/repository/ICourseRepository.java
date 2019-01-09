package my.java.vlong.homework3_refactor.repository;

import java.util.List;
import my.java.vlong.homework3_refactor.entity.Course;
import my.java.vlong.homework3_refactor.entity.Student;

public interface ICourseRepository extends IRepository<Course, Integer> {

    List<Student> getStudentsOfCourse(Course course);

    List<Course> findByNameContaining(String keyWord);
}

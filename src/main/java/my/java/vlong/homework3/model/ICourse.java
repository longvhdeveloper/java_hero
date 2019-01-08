package my.java.vlong.homework3.model;

import java.util.List;

public interface ICourse {
    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(Course course);

    Course getCourse(int id);

    List<Course> getCourses();

    List<Course> search(String keyword);
}

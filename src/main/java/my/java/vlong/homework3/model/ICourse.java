package my.java.vlong.homework3.model;

import java.util.List;

public interface ICourse {
    boolean addCourse(CourseEntity courseEntity);

    boolean updateCourse(CourseEntity courseEntity);

    boolean deleteCourse(CourseEntity courseEntity);

    CourseEntity getCourse(int id);

    List<CourseEntity> getCourses();

    List<CourseEntity> search(String keyword);
}

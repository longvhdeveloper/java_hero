package my.java.vlong.homework3_refactor.factory;

import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.entity.Course;

public class CourseFactory implements IFactory<Course, CourseDTO> {

    @Override
    public CourseDTO toDTO(Course t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course toEntity(CourseDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

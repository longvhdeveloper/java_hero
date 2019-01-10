package my.java.vlong.homework3_refactor.factory;

import java.util.stream.Collectors;
import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.entity.Course;

public class CourseFactory implements IFactory<Course, CourseDTO> {

    private StudentFactory studentFactory;

    public CourseFactory() {
        studentFactory = new StudentFactory();
    }

    @Override
    public CourseDTO toDTO(Course t) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(String.valueOf(t.getId()));
        courseDTO.setName(t.getName());
        if (!t.getStudents().isEmpty()) {
            courseDTO.setStudentDTOs(t.getStudents().stream().map(studentFactory::toDTO).collect(Collectors.toList()));
        }

        return courseDTO;
    }

    @Override
    public Course toEntity(CourseDTO t) {
        Course course = new Course();
        if (t.getId() != null) {
            course.setId(Integer.parseInt(t.getId()));
        }
        course.setName(t.getName());
        if (!t.getStudentDTOs().isEmpty()) {
            course.setStudents(t.getStudentDTOs().stream().map(studentFactory::toEntity).collect(Collectors.toList()));
        }

        return course;
    }

}

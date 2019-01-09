package my.java.vlong.homework3_refactor.service;

import java.util.List;
import java.util.stream.Collectors;
import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Course;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.exception.AddCourseException;
import my.java.vlong.homework3_refactor.factory.CourseFactory;
import my.java.vlong.homework3_refactor.factory.StudentFactory;
import my.java.vlong.homework3_refactor.repository.CourseRepositoryImpl;
import my.java.vlong.homework3_refactor.repository.ICourseRepository;

public class CourseService {

    private ICourseRepository courseRepository;
    private CourseFactory courseFactory;
    private StudentFactory studentFactory;

    public CourseService() {
        courseRepository = new CourseRepositoryImpl();
        courseFactory = new CourseFactory();
    }

    public CourseDTO add(CourseDTO courseDTO) throws AddCourseException {
        if (!isAddValid(courseDTO)) {
            throw new AddCourseException("Can not add course");
        }
        Course course = courseFactory.toEntity(courseDTO);
        return courseFactory.toDTO(courseRepository.add(course));
    }

    public CourseDTO update(CourseDTO courseDTO) throws UpdateCourseException {
        if (!isUpdateValid(courseDTO)) {
            throw new UpdateCourseException("Can not update course");
        }
        Course course = courseFactory.toEntity(courseDTO);
        return courseFactory.toDTO(course);
    }

    public boolean delete(int id) throws DeleteCourseException {
        if (id == 0) {
            throw new DeleteCourseException("Can not delete course");
        }
        Course course = courseRepository.findByOne(id);
        return courseRepository.delete(course);
    }

    public List<CourseDTO> findAll() throws ResultListEmptyException {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }
        return courses.stream().map(courseFactory::toDTO).collect(Collectors.toList());
    }

    public List<CourseDTO> search(String keyWord) throws ResultListEmptyException {
        List<Course> courses = courseRepository.findByNameContaining(keyWord);
        if (courses.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }
        return courses.stream().map(courseFactory::toDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentOfCourse(int id) throws CourseNotFoundException, ResultListEmptyException {
        Course course = courseRepository.findByOne(id);
        if (course == null) {
            throw new CourseNotFoundException("Course not found");
        }
        List<Student> students = courseRepository.getStudentsOfCourse(course);
        if (students.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }

        return students.stream().map(studentFactory::toDTO).collect(Collectors.toList());
    }

    private boolean isAddValid(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return false;
        }

        if (courseDTO.getName() == null) {
            return false;
        }

        if (courseDTO.getName().equals("")) {
            return false;
        }

        return true;
    }

    private boolean isUpdateValid(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return false;
        }

        if (courseDTO.getId() == null) {
            return false;
        }

        if (courseDTO.getId().equals("")) {
            return false;
        }

        if (courseDTO.getName() == null) {
            return false;
        }

        if (courseDTO.getName().equals("")) {
            return false;
        }
        return false;
    }
}

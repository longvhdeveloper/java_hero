package my.java.vlong.homework3_refactor.service;

import my.java.vlong.homework3_refactor.exception.CourseNotFoundException;
import my.java.vlong.homework3_refactor.exception.UpdateCourseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Course;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.exception.AddCourseException;
import my.java.vlong.homework3_refactor.exception.DeleteCourseException;
import my.java.vlong.homework3_refactor.exception.ResultListEmptyException;
import my.java.vlong.homework3_refactor.factory.CourseFactory;
import my.java.vlong.homework3_refactor.factory.StudentFactory;
import my.java.vlong.homework3_refactor.infrastructure.CourseRepositoryImplDB;
import my.java.vlong.homework3_refactor.repository.ICourseRepository;

public class CourseService {

    private final ICourseRepository courseRepository;
    private final CourseFactory courseFactory;
    private final StudentFactory studentFactory;

    public CourseService() {
        courseRepository = new CourseRepositoryImplDB();
        courseFactory = new CourseFactory();
        studentFactory = new StudentFactory();
    }

    public CourseDTO add(CourseDTO courseDTO) throws AddCourseException {
        if (!isAddValid(courseDTO)) {
            throw new AddCourseException("Can not add course");
        }
        Course course = courseFactory.toEntity(courseDTO);

        Optional<Course> courseOption = courseRepository.add(course);

        if (!courseOption.isPresent()) {
            throw new AddCourseException("Can not add course");
        }

        return courseFactory.toDTO(courseOption.get());
    }

    public CourseDTO update(int id, CourseDTO courseDTO) throws UpdateCourseException, CourseNotFoundException {
        Optional<Course> courseOptional = courseRepository.findByOne(id);

        if (!courseOptional.isPresent()) {
            throw new CourseNotFoundException("Course not found");
        }

        if (!isUpdateValid(courseDTO)) {
            throw new UpdateCourseException("Can not update course");
        }
        
        Course courseUpdate = courseOptional.get();
        courseUpdate.setName(courseDTO.getName());

        Optional<Course> courseOption = courseRepository.update(courseUpdate);

        if (!courseOption.isPresent()) {
            throw new UpdateCourseException("Can not update course");
        }
        
        return courseFactory.toDTO(courseUpdate);
    }

    public boolean delete(int id) throws DeleteCourseException {
        if (id == 0) {
            throw new DeleteCourseException("Can not delete course");
        }
        Optional<Course> course = courseRepository.findByOne(id);
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
        Optional<Course> course = courseRepository.findByOne(id);
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

        if (courseDTO.getName() == null) {
            return false;
        }

        if (courseDTO.getName().equals("")) {
            return false;
        }
        return true;
    }
}

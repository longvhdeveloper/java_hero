package my.java.vlong.homework3_refactor.service;

import my.java.vlong.homework3_refactor.exception.DataNotFoundException;
import my.java.vlong.homework3_refactor.exception.UpdatedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Course;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.exception.AddedException;
import my.java.vlong.homework3_refactor.exception.DeletedException;
import my.java.vlong.homework3_refactor.exception.ResultListEmptyException;
import my.java.vlong.homework3_refactor.infrastructure.CourseRepositoryImplDB;
import my.java.vlong.homework3_refactor.mapping.CourseMapper;
import my.java.vlong.homework3_refactor.mapping.StudentMapper;
import my.java.vlong.homework3_refactor.repository.ICourseRepository;

public class CourseService {

    private final ICourseRepository courseRepository;

    public CourseService() {
        courseRepository = new CourseRepositoryImplDB();
    }

    public CourseDTO add(CourseDTO courseDTO) throws AddedException {
        if (!isAddValid(courseDTO)) {
            throw new AddedException("Can not add course");
        }
        Course course = CourseMapper.INSTANCE.toEntity(courseDTO);

        Optional<Course> courseOption = courseRepository.add(course);

        if (!courseOption.isPresent()) {
            throw new AddedException("Can not add course");
        }

        return CourseMapper.INSTANCE.toDTO(courseOption.get());
    }

    public CourseDTO update(int id, CourseDTO courseDTO) throws UpdatedException, DataNotFoundException {
        Optional<Course> courseOptional = courseRepository.findByOne(id);

        if (!courseOptional.isPresent()) {
            throw new DataNotFoundException("Course not found");
        }

        if (!isUpdateValid(courseDTO)) {
            throw new UpdatedException("Can not update course");
        }

        Course courseUpdate = courseOptional.get();
        Course data = CourseMapper.INSTANCE.toEntity(courseDTO);

        courseUpdate.setName(data.getName());

        Optional<Course> courseOption = courseRepository.update(courseUpdate);

        if (!courseOption.isPresent()) {
            throw new UpdatedException("Can not update course");
        }

        return CourseMapper.INSTANCE.toDTO(courseOption.get());
    }

    public boolean delete(int id) throws DeletedException {
        Optional<Course> courseOptional = courseRepository.findByOne(id);
        if (!courseOptional.isPresent()) {
            throw new DeletedException("Can not delete course");
        }
        return courseRepository.delete(courseOptional);
    }

    public List<CourseDTO> findAll() throws ResultListEmptyException {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }
        //return courses.stream().map(CourseMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return CourseMapper.INSTANCE.toDTOs(courses);
    }

    public List<CourseDTO> search(String keyWord) throws ResultListEmptyException {
        List<Course> courses = courseRepository.findByNameContaining(keyWord);
        if (courses.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }
        //return courses.stream().map(CourseMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return CourseMapper.INSTANCE.toDTOs(courses);
    }

    public List<StudentDTO> getStudentOfCourse(int id) throws DataNotFoundException, ResultListEmptyException {
        Optional<Course> course = courseRepository.findByOne(id);
        List<Student> students = courseRepository.getStudentsOfCourse(course);
        if (students.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }

        //return students.stream().map(StudentMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return StudentMapper.INSTANCE.toDTOs(students);
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

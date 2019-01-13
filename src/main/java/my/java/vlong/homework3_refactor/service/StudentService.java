package my.java.vlong.homework3_refactor.service;

import java.util.List;
import java.util.Optional;
import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.exception.AddedException;
import my.java.vlong.homework3_refactor.exception.DataNotFoundException;
import my.java.vlong.homework3_refactor.exception.DeletedException;
import my.java.vlong.homework3_refactor.exception.UpdatedException;
import my.java.vlong.homework3_refactor.infrastructure.StudentRepositoryImplDB;
import my.java.vlong.homework3_refactor.mapping.StudentMapper;
import my.java.vlong.homework3_refactor.repository.IStudentRepository;

public class StudentService {

    private final IStudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepositoryImplDB();
    }

    public StudentDTO add(StudentDTO studentDTO) throws AddedException {
        if (!isAddValid(studentDTO)) {
            throw new AddedException("Can not add student");
        }

        Student student = StudentMapper.INSTANCE.toEntity(studentDTO);

        Optional<Student> studentOptional = studentRepository.add(student);
        if (!studentOptional.isPresent()) {
            throw new AddedException("Can not add student");
        }

        return StudentMapper.INSTANCE.toDTO(studentOptional.get());
    }

    public StudentDTO update(int id, StudentDTO studentDTO) throws UpdatedException, DataNotFoundException {
        Optional<Student> studentOptional = studentRepository.findByOne(id);

        if (!studentOptional.isPresent()) {
            throw new DataNotFoundException("Student not found");
        }

        if (isUpdateValid(studentDTO)) {
            throw new UpdatedException("Can not update student");
        }

        Student studentUpdate = studentOptional.get();

        Student data = StudentMapper.INSTANCE.toEntity(studentDTO);

        studentUpdate.setId(data.getId());
        studentUpdate.setGender(data.getGender());
        studentUpdate.setName(data.getName());
        studentUpdate.setDateOfBirth(data.getDateOfBirth());
        studentUpdate.setCourse(data.getCourse());

        Optional<Student> studentOption = studentRepository.update(studentUpdate);

        if (!studentOption.isPresent()) {
            throw new UpdatedException("Can not update student");
        }

        return StudentMapper.INSTANCE.toDTO(studentOption.get());
    }

    public boolean delete(int id) throws DeletedException {
        Optional<Student> studentOptional = studentRepository.findByOne(id);
        if (!studentOptional.isPresent()) {
            throw new DeletedException("Can not delete student");
        }
        
        return studentRepository.delete(studentOptional);
    }

    public List<StudentDTO> findAll() {
        return null;
    }

    public List<StudentDTO> search(String keyWord) {
        return null;
    }

    private boolean isAddValid(StudentDTO studentDTO) {
        return true;
    }

    private boolean isUpdateValid(StudentDTO studentDTO) {
        return true;
    }
}

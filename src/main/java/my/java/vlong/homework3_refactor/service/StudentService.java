package my.java.vlong.homework3_refactor.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Gender;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.exception.AddedException;
import my.java.vlong.homework3_refactor.exception.DataNotFoundException;
import my.java.vlong.homework3_refactor.exception.DeletedException;
import my.java.vlong.homework3_refactor.exception.ResultListEmptyException;
import my.java.vlong.homework3_refactor.exception.UpdatedException;
import my.java.vlong.homework3_refactor.infrastructure.StudentRepositoryImplDB;
import my.java.vlong.homework3_refactor.mapping.StudentMapper;
import my.java.vlong.homework3_refactor.repository.IStudentRepository;

public class StudentService {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

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

    public List<StudentDTO> findAll() throws ResultListEmptyException {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }
        return StudentMapper.INSTANCE.toDTOs(students);
    }

    public List<StudentDTO> search(String keyWord) throws ResultListEmptyException {
        List<Student> students = studentRepository.findByNameContaining(keyWord);
        if (students.isEmpty()) {
            throw new ResultListEmptyException("Result list is empty");
        }
        return StudentMapper.INSTANCE.toDTOs(students);
    }

    private boolean isAddValid(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return false;
        }

        if (studentDTO.getName().equals("")) {
            return false;
        }

        if (studentDTO.getDateOfBirth().equals("")) {
            return false;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            simpleDateFormat.parse(studentDTO.getDateOfBirth());
        } catch (ParseException ex) {
            return false;
        }

        if (Gender.valueOf(Integer.parseInt(studentDTO.getGender())) == null) {
            return false;
        }

        if (studentDTO.getCourse().equals("")) {
            return false;
        }

        return true;
    }

    private boolean isUpdateValid(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return false;
        }

        if (studentDTO.getName().equals("")) {
            return false;
        }

        if (studentDTO.getDateOfBirth().equals("")) {
            return false;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            simpleDateFormat.parse(studentDTO.getDateOfBirth());
        } catch (ParseException ex) {
            return false;
        }

        if (Gender.valueOf(Integer.parseInt(studentDTO.getGender())) == null) {
            return false;
        }

        if (studentDTO.getCourse().equals("")) {
            return false;
        }

        return true;
    }
}

package my.java.vlong.homework3_refactor.factory;

import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Student;

public class StudentFactory implements IFactory<Student, StudentDTO> {

    @Override
    public StudentDTO toDTO(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Student toEntity(StudentDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

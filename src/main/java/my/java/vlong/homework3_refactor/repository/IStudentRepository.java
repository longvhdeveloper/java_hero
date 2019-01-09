package my.java.vlong.homework3_refactor.repository;

import java.util.List;
import my.java.vlong.homework3_refactor.entity.Student;

public interface IStudentRepository extends IRepository<Student, Integer> {

    List<Student> findByNameContaining(String keyWord);
}

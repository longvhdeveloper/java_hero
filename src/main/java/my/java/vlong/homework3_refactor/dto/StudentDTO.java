package my.java.vlong.homework3_refactor.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private String id;
    private String name;
    private String gender;
    private String dateOfBirth;
    private CourseDTO course;

    @Override
    public String toString() {
        return "StudentDTO{" + "id=" + id + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", course=" + course + '}';
    }

}

package my.java.vlong.homework3_refactor.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {

    private String id;
    private String name;
    private List<StudentDTO> studentDTOs;

    public CourseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentDTO> getStudentDTOs() {
        return studentDTOs != null ? studentDTOs : new ArrayList<>();
    }

    public void setStudentDTOs(List<StudentDTO> studentDTOs) {
        this.studentDTOs = studentDTOs;
    }

    @Override
    public String toString() {
        return "CourseDTO{" + "id=" + id + ", name=" + name + ", studentDTOs=" + studentDTOs + '}';
    }
}

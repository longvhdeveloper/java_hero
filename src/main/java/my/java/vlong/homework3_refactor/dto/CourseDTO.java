package my.java.vlong.homework3_refactor.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CourseDTO {

    private String id;
    private String name;
    private List<StudentDTO> studentDTOs;

    @Override
    public String toString() {
        return "CourseDTO{" + "id=" + id + ", name=" + name + ", studentDTOs=" + studentDTOs + '}';
    }
}

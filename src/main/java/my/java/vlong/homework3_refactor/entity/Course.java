package my.java.vlong.homework3_refactor.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Course {

    private static int COUNTER = 0;

    private int id;
    private String name;
    private List<Student> students;

    public Course() {
        this.id = ++COUNTER;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", students=" + students.size() + '}';
    }

}

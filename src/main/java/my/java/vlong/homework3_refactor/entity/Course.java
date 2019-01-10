package my.java.vlong.homework3_refactor.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private static int COUNTER = 0;

    private int id;
    private String name;
    private List<Student> students;

    public Course() {
        this.id = ++COUNTER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students == null ? new ArrayList<>() : students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", students=" + students.size() + '}';
    }

}

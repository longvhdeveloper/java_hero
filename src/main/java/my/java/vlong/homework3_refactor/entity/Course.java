package my.java.vlong.homework3_refactor.entity;

import java.util.List;

public class Course {

    private static int COUNTER = 0;

    private final int id;
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
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", students=" + students.size() + '}';
    }

}

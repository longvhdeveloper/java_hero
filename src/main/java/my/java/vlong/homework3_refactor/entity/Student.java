package my.java.vlong.homework3_refactor.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Student {

    private static int COUNTER = 0;

    private int id;
    private String name;
    private Date dateOfBirth;
    private Gender gender;
    private Course course;

    public Student() {
        this.id = ++COUNTER;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", course=" + course + '}';
    }

}

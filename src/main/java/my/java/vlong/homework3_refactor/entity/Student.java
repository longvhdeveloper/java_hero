package my.java.vlong.homework3_refactor.entity;

import java.sql.Date;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", course=" + course + '}';
    }

}

package my.java.vlong.homework3.model;

import java.util.Date;

public class Student {
    private int id;
    private String fullName;
    private Date dateOfBirth;
    private Gender gender;
    private Course course;

    public Student() {
    }

    public Student(int id, String fullName, Date dateOfBirth, Gender gender, Course course) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.course = course;
    }

    public Student(String fullName, Date dateOfBirth, Gender gender, Course course) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public Course getCourse() {
        return course;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isValid() {
        if (fullName == null || "".equals(fullName)) {
            return false;
        }

        if (dateOfBirth == null) {
            return false;
        }

        if (gender == null) {
            return false;
        }

        if (course == null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format("Student: {id: %d, full name: %s, date of birth: %s, gender: %s, course: %s}", id,
                fullName, dateOfBirth.toString(), Gender.getValue(gender), course.getName());
    }
}

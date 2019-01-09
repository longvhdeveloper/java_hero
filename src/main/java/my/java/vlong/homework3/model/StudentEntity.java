package my.java.vlong.homework3.model;

import java.util.Date;

public class StudentEntity {
    private int id;
    private String fullName;
    private Date dateOfBirth;
    private Gender gender;
    private CourseEntity courseEntity;

    public StudentEntity() {
    }

    public StudentEntity(int id, String fullName, Date dateOfBirth, Gender gender, CourseEntity courseEntity) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.courseEntity = courseEntity;
    }

    public StudentEntity(String fullName, Date dateOfBirth, Gender gender, CourseEntity courseEntity) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.courseEntity = courseEntity;
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

    public CourseEntity getCourseEntity() {
        return courseEntity;
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

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
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

        if (courseEntity == null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format("StudentEntity: {id: %d, full name: %s, date of birth: %s, gender: %s, courseEntity: %s}", id,
                fullName, dateOfBirth.toString(), Gender.getValue(gender), courseEntity.getName());
    }
}

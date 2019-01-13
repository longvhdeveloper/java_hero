package my.java.vlong.homework3_refactor.dto;

public class StudentDTO {

    private String id;
    private String name;
    private String gender;
    private String dateOfBirth;
    private CourseDTO course;

    public StudentDTO() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "id=" + id + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", course=" + course + '}';
    }

}

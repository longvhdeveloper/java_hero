package my.java.vlong.practice3.model;

import java.util.Date;

public abstract class EmployeeEntity {
    private static int COUNTER = 0;

    private int id;
    private String fullName;
    private Date dateOfBirth;
    private float salary;

    public EmployeeEntity() {
    }

    public EmployeeEntity(int id, String fullName, Date dateOfBirth, float salary) {
        this.id = ++COUNTER;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
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

    public float getSalary() {
        return salary;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public abstract float calculateSalary();
}

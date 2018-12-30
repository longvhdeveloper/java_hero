package my.java.vlong.practice1;

import java.util.Date;

public abstract class Employee {

    private int id;
    private String fullName;
    private Date birthday;
    private float salary;

    public Employee(int id, String fullName, Date birthday, float salary) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public float getSalary() {
        return salary;
    }

    public abstract float calculateIncome();
}

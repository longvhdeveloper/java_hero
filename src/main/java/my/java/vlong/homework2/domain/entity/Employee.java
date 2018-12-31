package my.java.vlong.homework2.domain.entity;

public abstract class Employee implements IEmployee {
    private static int AUTO_INCREMENT = 0;

    private int id;
    private String fullName;
    private Gender gender;
    private float salary;
    private Position position;

    public Employee(String fullName, Gender gender, float salary) {
        this.id = ++AUTO_INCREMENT;
        this.fullName = fullName;
        this.gender = gender;
        this.salary = salary;

    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public float getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isValidData() {
        if (this.fullName == null) {
            return false;
        }

        if ("".equals(this.fullName)) {
            return false;
        }

        if (this.gender == null) {
            return false;
        }

        if (this.salary <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public void displayInfo() {
        System.out.println(String.format("Id: %d", id));
        System.out.println(String.format("FullName: %s", fullName));
        System.out.println(String.format("Gender: %s", Gender.getValue(gender)));
        System.out.println(String.format("Salary: %.2f", salary));
    }
}

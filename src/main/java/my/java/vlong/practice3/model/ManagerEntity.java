package my.java.vlong.practice3.model;

import java.util.Date;

public class ManagerEntity extends EmployeeEntity {
    private int numberOfWorker;
    private int baseNumber;

    public ManagerEntity() {

    }

    public ManagerEntity(int id, String fullName, Date dateOfBirth, float salary, int numberOfWorker, int baseNumber) {
        super(id, fullName, dateOfBirth, salary);
        this.numberOfWorker = numberOfWorker;
        this.baseNumber = baseNumber;
    }

    public int getNumberOfWorker() {
        return numberOfWorker;
    }

    public int getBaseNumber() {
        return baseNumber;
    }

    public void setNumberOfWorker(int numberOfWorker) {
        this.numberOfWorker = numberOfWorker;
    }

    public void setBaseNumber(int baseNumber) {
        this.baseNumber = baseNumber;
    }

    @Override
    public float calculateSalary() {
        return this.getSalary() + this.getBaseNumber() * this.getNumberOfWorker();
    }
}

package my.java.vlong.practice3.model;

import java.util.Date;

public class WorkerEntity extends EmployeeEntity {

    private int daysOfWorking;
    private int baseNumber;

    public WorkerEntity() {

    }

    public WorkerEntity(int id, String fullName, Date dateOfBirth, float salary, int daysOfWorking, int baseNumber) {
        super(id, fullName, dateOfBirth, salary);
        this.daysOfWorking = daysOfWorking;
        this.baseNumber = baseNumber;
    }

    public int getDaysOfWorking() {
        return daysOfWorking;
    }

    public int getBaseNumber() {
        return baseNumber;
    }

    public void setDaysOfWorking(int daysOfWorking) {
        this.daysOfWorking = daysOfWorking;
    }

    public void setBaseNumber(int baseNumber) {
        this.baseNumber = baseNumber;
    }

    @Override
    public float calculateSalary() {
        return this.getSalary() + this.getBaseNumber() * this.getDaysOfWorking();
    }
}

package my.java.vlong.practice3.model;

import java.util.Date;

public class DirectorEntity extends EmployeeEntity {
    private float revenue;

    public DirectorEntity() {

    }

    public DirectorEntity(int id, String fullName, Date dateOfBirth, float salary, float revenue) {
        super(id, fullName, dateOfBirth, salary);
        this.revenue = revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public float getRevenue() {
        return revenue;
    }

    @Override
    public float calculateSalary() {
        return this.getSalary() + this.getRevenue();
    }
}

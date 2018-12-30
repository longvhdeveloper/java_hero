package my.java.vlong.practice1;

import java.util.Date;

public class Director extends Employee {

    private float revenue;

    public Director(float revenue, int id, String fullName, Date birthday, float salary) {
        super(id, fullName, birthday, salary);
        this.revenue = revenue;
    }

    public float getRevenue() {
        return revenue;
    }

    @Override
    public float calculateIncome() {
        return this.getSalary() + this.revenue;
    }

}

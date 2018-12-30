package my.java.vlong.practice1;

import java.util.Date;

public class Worker extends Employee {

    private int dayWorkingInMonths;
    private int weight;

    public Worker(int dayWorkingInMonths, int weight, int id, String fullName, Date birthday, float salary) {

        super(id, fullName, birthday, salary);
        this.dayWorkingInMonths = dayWorkingInMonths;
        this.weight = weight;
    }

    public int getDayWorkingInMonths() {
        return dayWorkingInMonths;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public float calculateIncome() {
        return this.getSalary() + this.dayWorkingInMonths * this.weight;
    }

}

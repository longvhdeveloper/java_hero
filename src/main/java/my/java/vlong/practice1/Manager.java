package my.java.vlong.practice1;

import java.util.Date;

public class Manager extends Employee {

    private int numberOfWorkerManage;
    private int weight;

    public Manager(int numberOfWorkerManage, int weight, int id, String fullName, Date birthday, float salary) {
        super(id, fullName, birthday, salary);
        this.numberOfWorkerManage = numberOfWorkerManage;
        this.weight = weight;
    }

    public int getNumberOfWorkerManage() {
        return numberOfWorkerManage;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public float calculateIncome() {
        return this.getSalary() + this.numberOfWorkerManage * this.weight;
    }

}

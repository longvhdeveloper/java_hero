package my.java.vlong.homework2.domain.entity;

import my.java.vlong.homework2.domain.exception.AllowanceException;

public class Manager extends Employee {
    private int numberOfEmployee;

    public Manager(String fullName, Gender gender, float salary, int numberOfEmployee) {
        super(fullName, gender, salary);
        this.numberOfEmployee = numberOfEmployee;
        this.setPosition(Position.MANAGER);
    }

    public int getNumberOfEmployee() {
        return numberOfEmployee;
    }

    @Override
    public float calculateSalary() {
        try {
            int allowance = Allowance.getAllowance(this.getNumberOfEmployee());
            return this.getSalary() + allowance;
        } catch (AllowanceException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean isValidData() {
        if (!super.isValidData()) {
            return false;
        }

        if (numberOfEmployee <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(String.format("Position: %s", Position.getValue(this.getPosition())));
        System.out.println(String.format("Number of employees: %d", numberOfEmployee));
        System.out.println(String.format("Total salary: %.2f", calculateSalary()));
    }
}

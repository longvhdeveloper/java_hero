package my.java.vlong.homework2.domain.entity;

public class Worker extends Employee {
    private static final int DAY_DEFAULT = 26;

    private int workOfDays;

    public Worker(String fullName, Gender gender, float salary, int workOfDays) {
        super(fullName, gender, salary);
        this.workOfDays = workOfDays;
        this.setPosition(Position.WORKER);
    }

    public int getWorkOfDays() {
        return workOfDays;
    }

    @Override
    public float calculateSalary() {
        return (float) (this.getSalary() + ((this.getWorkOfDays() / 26.0) * this.getSalary()));
    }

    @Override
    public boolean isValidData() {
        if (!super.isValidData()) {
            return false;
        }

        if (workOfDays <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(String.format("Position: %s", Position.getValue(this.getPosition())));
        System.out.println(String.format("Day of works: %d", workOfDays));
        System.out.println(String.format("Total salary: %.2f", calculateSalary()));
    }
}

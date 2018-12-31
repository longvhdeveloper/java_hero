package my.java.vlong.homework2.domain.entity;

public class Officer extends Employee {

    private float weight;

    public Officer(String fullName, Gender gender, float salary, float weight) {
        super(fullName, gender, salary);
        this.weight = weight;
        this.setPosition(Position.OFFICER);
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public float calculateSalary() {
        return this.getSalary() + (this.getWeight() * this.getSalary());
    }

    @Override
    public boolean isValidData() {
        if (!super.isValidData()) {
            return false;
        }

        if (this.weight <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(String.format("Position: %s", Position.getValue(this.getPosition())));
        System.out.println(String.format("Weight: %.2f", weight));
        System.out.println(String.format("Total salary: %.2f", calculateSalary()));
    }
}

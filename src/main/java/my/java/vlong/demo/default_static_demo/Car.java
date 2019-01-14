package my.java.vlong.demo.default_static_demo;

public class Car implements Vehicle {

    private String brand;

    public Car(String brand) {
        this.brand = brand;
    }

    // constructors/getters
    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String speedUp() {
        return "The car is speeding up.";
    }

    @Override
    public String slowDown() {
        return "The car is slowing down.";
    }
}

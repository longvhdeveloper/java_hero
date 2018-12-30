package my.java.vlong.practice2;

import java.util.Scanner;

public class DigitalProduct extends Product {

    private static final int NUMBER = 1000;

    private int volume;

    public void inputProduct() throws ProductException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Digital Product Information ===");

        System.out.println("Name: ");
        String name = scanner.nextLine();
        this.setName(name);

        System.out.println("Volume: ");
        int volume = scanner.nextInt();
        this.setVolume(volume);

        // calculate sale price
        this.setSalePrice(this.calculateSalePrice());

        // validate data
        scanner.close();
    }

    public void displayProduct() {
        System.out.println("=== Digital Product Information ===");
        System.out.println(String.format("Id: %d", this.getId()));
        System.out.println(String.format("Name: %s", this.getName()));
        System.out.println(String.format("Volume: %d", this.getVolume()));
        System.out.println(String.format("Sale price: %.2f", this.getSalePrice()));
        System.out.println("====================================");
    }

    public float calculateSalePrice() {
        return this.volume * NUMBER;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) throws ProductException {
        if (!this.validateVolume(volume)) {
            throw new ProductException("Volume is not valid");
        }
        this.volume = volume;
    }

    private boolean validateVolume(int volume) {
        return volume > 0;
    }
}

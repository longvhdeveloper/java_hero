package my.java.vlong.practice2;

import java.util.Scanner;

public class AgricultureProduct extends Product {

    private static final int NUMBER = 500;

    private int expireTime;
    private float currentPrice;

    public void inputProduct() throws ProductException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Industry Product Information ===");

        System.out.println("Name: ");
        String name = scanner.nextLine();
        this.setName(name);

        System.out.println("Expire time: ");
        int expireTime = scanner.nextInt();
        this.setExpireTime(expireTime);

        System.out.println("Current price: ");
        float currentPrice = scanner.nextFloat();
        this.setCurrentPrice(currentPrice);

        // calculate sale price
        this.setSalePrice(this.calculateSalePrice());

        // validate data
        scanner.close();
    }

    public void displayProduct() {
        System.out.println("=== Digital Product Information ===");
        System.out.println(String.format("Id: %d", this.getId()));
        System.out.println(String.format("Name: %s", this.getName()));
        System.out.println(String.format("Expire time: %d", this.getExpireTime()));
        System.out.println(String.format("Current price: %.2f", this.getCurrentPrice()));
        System.out.println(String.format("Sale price: %.2f", this.getSalePrice()));
        System.out.println("====================================");
    }

    public float calculateSalePrice() {
        return this.currentPrice + this.expireTime * NUMBER;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    private boolean validateExpireTime(int expireTime) {
        return expireTime > 0;
    }

    private boolean validateCurrentPrice(float currentPrice) {
        return currentPrice > 0;
    }
}

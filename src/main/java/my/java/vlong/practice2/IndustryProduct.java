package my.java.vlong.practice2;

import java.util.Scanner;

public class IndustryProduct extends Product {

    private static final int NUMBER = 1000;

    private int warrantyTime;
    private float originalPrice;

    public void inputProduct() throws ProductException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Industry Product Information ===");

        System.out.println("Name: ");
        String name = scanner.nextLine();
        this.setName(name);

        System.out.println("Warranty time: ");
        int warrantyTime = scanner.nextInt();
        this.setWarrantyTime(warrantyTime);

        System.out.println("Original price: ");
        float originalPrice = scanner.nextFloat();
        this.setOriginalPrice(originalPrice);

        // calculate sale price
        this.setSalePrice(this.calculateSalePrice());

        // validate data
        scanner.close();
    }

    public void displayProduct() {
        System.out.println("=== Digital Product Information ===");
        System.out.println(String.format("Id: %d", this.getId()));
        System.out.println(String.format("Name: %s", this.getName()));
        System.out.println(String.format("Warraty time: %d", this.getWarrantyTime()));
        System.out.println(String.format("Original price: %.2f", this.getOriginalPrice()));
        System.out.println(String.format("Sale price: %.2f", this.getSalePrice()));
        System.out.println("====================================");
    }

    public float calculateSalePrice() {
        return this.originalPrice + this.warrantyTime * NUMBER;
    }

    public int getWarrantyTime() {
        return warrantyTime;
    }

    public void setWarrantyTime(int warrantyTime) throws ProductException {
        if (!this.validateWarrantyTime(warrantyTime)) {
            throw new ProductException("Warranty time of product is not valid");
        }
        this.warrantyTime = warrantyTime;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) throws ProductException {
        if (!this.validateOriginalPrice(originalPrice)) {
            throw new ProductException("Original price of product is not valid");
        }
        this.originalPrice = originalPrice;
    }

    private boolean validateWarrantyTime(int warrantyTime) {
        return warrantyTime > 0;
    }

    private boolean validateOriginalPrice(float originalPrice) {
        return originalPrice > 0;
    }
}

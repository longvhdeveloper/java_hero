package my.java.vlong.practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {

    private List<Product> products;

    public ProductManagement() {
        this.products = new ArrayList<>();
    }

    private void displayMenu() {
        System.out.println("=== Product Management System ===");
        System.out.println("1. Entry product.");
        System.out.println("2. Display product.");
        System.out.println("3. Find product by max price.");
        System.out.println("4. Exit.");
        System.out.println("Enter your choice (1 - 4): ");
    }

    private void displayMenuEntryProduct() {
        System.out.println("=== Product Type ===");
        System.out.println("1. Digital product.");
        System.out.println("2. Indsutry product.");
        System.out.println("3. Agricultrue product .");
        System.out.println("4. Exit.");
        System.out.println("Enter your choice (1 - 4): ");
    }

    private void executeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            this.displayMenu();
            choice = scanner.nextInt();

            MenuItem choiceMenuItem = MenuItem.valueOf(choice);

            switch (choiceMenuItem) {
                case ENTRY_PRODUCT:
                    this.entryProduct();
                    break;
                case DISPLAY_PRODUCT:
                    this.displayProduct();
                    break;
                case FIND_PRODUCT_BY_MAX_PRICE:
                    this.findProductByMaxPrice();
                    break;
            }

            if (choice == 4) {
                break;
            }
        } while (choice != 4);

        System.out.println("Thank you and see you next time.");
        scanner.close();
    }

    private void entryProduct() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            displayMenuEntryProduct();
            choice = scanner.nextInt();
            
            
        } while (choice != 4);

        scanner.close();
    }

    private void displayProduct() {

    }

    private void findProductByMaxPrice() {

    }

    public void run() {
        this.executeMenu();
    }

}

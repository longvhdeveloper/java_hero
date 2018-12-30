package my.java.vlong.practice2;

public abstract class Product implements IProduct {

    private static int COUNTER = 1;

    private int id;
    private String name;
    private float salePrice;

    public Product() {
        this.id = COUNTER;

        COUNTER++;
    }

    public Product(String name) {
        this.id = COUNTER;
        this.name = name;

        COUNTER++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ProductException {
        if (!this.validateName(name)) {
            throw new ProductException("Product name is not valid");
        }
        this.name = name;
    }

    public float getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(float salePrice) throws ProductException {
        if (salePrice <= 0) {
            throw new ProductException(String.format("Sale price of product have value %.2f is not valid", this.salePrice));
        }
        this.salePrice = salePrice;
    }

    private boolean validateName(String name) {
        if (name == null || "".equals(name)) {
            return false;
        }
        return true;
    }
}

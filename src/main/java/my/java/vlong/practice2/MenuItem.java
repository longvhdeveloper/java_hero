package my.java.vlong.practice2;

public enum MenuItem {
    ENTRY_PRODUCT(1), DISPLAY_PRODUCT(2), FIND_PRODUCT_BY_MAX_PRICE(3), EXIT(4);

    private final int index;

    private MenuItem(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static MenuItem valueOf(int index) {
        switch (index) {
            case 1:
                return ENTRY_PRODUCT;
            case 2:
                return DISPLAY_PRODUCT;
            case 3:
                return FIND_PRODUCT_BY_MAX_PRICE;
            default:
                return EXIT;
        }
    }

}

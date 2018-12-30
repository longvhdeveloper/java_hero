package my.java.vlong.homework2.domain.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MenuItem {
    ENTRY_EMPLOYEES(1), DISPLAY_EMPLOYEES(2), FIND_EMPLOYEE_BY_SALARY(3), EXIT(4), DEFAULT(0);

    private int code;

    MenuItem(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MenuItem valueOf(int code) {
        switch (code) {
            case 1:
                return ENTRY_EMPLOYEES;
            case 2:
                return DISPLAY_EMPLOYEES;
            case 3:
                return FIND_EMPLOYEE_BY_SALARY;
            case 4:
                return EXIT;
        }
        return DEFAULT;
    }

    public static boolean valueIsValid(MenuItem itemChoice) {
        List<MenuItem> menuItemList = Arrays.asList(MenuItem.values()).stream().filter(menuItem -> !menuItem.equals(DEFAULT)).collect(Collectors.toList());
        return menuItemList.contains(itemChoice);
    }
}

package my.java.vlong.homework2.domain.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EmployeeMenuItem {
    WORKER(1), OFFICER(2), MANAGER(3), RETURN_MAIN(4), DEFAULT(0);

    private int code;

    EmployeeMenuItem(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static EmployeeMenuItem valueOf(int code) {
        switch (code) {
            case 1:
                return WORKER;
            case 2:
                return OFFICER;
            case 3:
                return MANAGER;
            case 4:
                return RETURN_MAIN;
        }
        return DEFAULT;
    }

    public static boolean valueIsValid(EmployeeMenuItem itemChoice) {
        List<EmployeeMenuItem> menuItemList =
                Arrays.stream(EmployeeMenuItem.values()).filter(menuItem -> !menuItem.equals(DEFAULT)).collect(Collectors.toList());
        return menuItemList.contains(itemChoice);
    }
}

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

    public enum EmployeeFindConditionMenuItem {
        FIND_BY_GENDER(1), FIND_BY_POSITION(2), RETURN_MAIN(3), DEFAULT(0);
        private int code;

        EmployeeFindConditionMenuItem(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static EmployeeFindConditionMenuItem valueOf(int code) {
            switch (code) {
                case 1:
                    return FIND_BY_GENDER;
                case 2:
                    return FIND_BY_POSITION;
            }
            return DEFAULT;
        }

        public static boolean valueIsValid(EmployeeFindConditionMenuItem itemChoice) {
            List<EmployeeFindConditionMenuItem> menuItemList = Arrays.asList(EmployeeFindConditionMenuItem.values()).stream().filter(menuItem -> !menuItem.equals(DEFAULT)).collect(Collectors.toList());
            return menuItemList.contains(itemChoice);
        }
    }
}

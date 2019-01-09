package my.java.vlong.homework3.controller;

public enum Menu {
    MAIN_STUDENT(1), MAIN_COURSE(2), EXIT(3);
    private int code;

    Menu(int code) {
        this.code = code;
    }

    public static Menu valueOf(int code) {
        switch (code) {
            case 1:
                return MAIN_STUDENT;
            case 2:
                return MAIN_COURSE;
            case 3:
                return EXIT;
        }
        return null;
    }

    public enum MenuCourse {
        ADD(1), UPDATE(2), DELETE(3), SEARCH(4), VIEW_ALL(5), VIEW_STUDENT_OF_COURSE(6), RETURN_MAIN(7);
        private int code;

        MenuCourse(int code) {
            this.code = code;
        }

        public static MenuCourse valueOf(int code) {
            switch (code) {
                case 1:
                    return ADD;
                case 2:
                    return UPDATE;
                case 3:
                    return DELETE;
                case 4:
                    return SEARCH;
                case 5:
                    return VIEW_ALL;
                case 6:
                    return VIEW_STUDENT_OF_COURSE;
                case 7:
                    return RETURN_MAIN;
            }
            return null;
        }
    }

    public enum MenuStudent {
        ADD(1), UPDATE(2), DELETE(3), SEARCH(4), VIEW_ALL(5), RETURN_MAIN(6);
        private int code;

        MenuStudent(int code) {
            this.code = code;
        }

        public static MenuStudent valueOf(int code) {
            switch (code) {
                case 1:
                    return ADD;
                case 2:
                    return UPDATE;
                case 3:
                    return DELETE;
                case 4:
                    return SEARCH;
                case 5:
                    return VIEW_ALL;
                case 6:
                    return RETURN_MAIN;
            }
            return null;
        }
    }
}

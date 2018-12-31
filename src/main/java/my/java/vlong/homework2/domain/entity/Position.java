package my.java.vlong.homework2.domain.entity;

public enum Position {
    WORKER(1), OFFICER(2), MANAGER(3);

    private int code;

    Position(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Position valueOf(int code) {
        switch (code) {
            case 1:
                return WORKER;
            case 0:
                return OFFICER;
            case 3:
                return MANAGER;
        }
        return null;
    }

    public static String getValue(Position position) {
        switch (position) {
            case WORKER:
                return "Worker";
            case OFFICER:
                return "Officer";
            case MANAGER:
                return "Manager";
        }
        return "";
    }
}

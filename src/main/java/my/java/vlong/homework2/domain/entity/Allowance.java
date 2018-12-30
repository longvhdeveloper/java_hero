package my.java.vlong.homework2.domain.entity;

import my.java.vlong.homework2.domain.exception.AllowanceException;

public class Allowance {
    private static final int ALLOWANCE_LEVEL1 = 500;
    private static final int ALLOWANCE_LEVEL2 = 1000;
    private static final int ALLOWANCE_LEVEL3 = 2000;

    public static int getAllowance(int numberOfEmployee) throws AllowanceException {
        if (numberOfEmployee < 0) {
            throw new AllowanceException("Number of employee to calculate allowance is not valid.");
        }

        if (numberOfEmployee < 10) {
            return ALLOWANCE_LEVEL1;
        } else if (numberOfEmployee < 20) {
            return ALLOWANCE_LEVEL2;
        }

        return ALLOWANCE_LEVEL3;
    }
}

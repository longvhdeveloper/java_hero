package my.java.vlong.homework1;

public class Student {
    private static final int numberOfItem = 3;
    private static int COUNTER = 0;
    private static int INCREASE_NUMBER = 0;

    private int id;
    private boolean active;

    private Student() {

        this.id = ++INCREASE_NUMBER;
        // Default student is created with active is true
        this.active = true;

        COUNTER++;
        System.out.println("Create student with id: " + id);
    }

    public static Student createStudent() throws StudentCreatedException {
        if (COUNTER < numberOfItem) {
            return new Student();
        }

        throw new StudentCreatedException("Maximum of student can create is " + numberOfItem);
    }


    public void setActive(boolean active) throws StudentActivedException {
        // If set status is same with current status , will ignore
        if (active && this.active) {
            return;
        }

        // case set active is false -> true
        if (active) {
            // Check counter is limit ?
            if (COUNTER >= numberOfItem) {
                throw new StudentActivedException("Maximum of student can be active is " + numberOfItem);
            }

            this.active = true;
            System.out.println("StudentEntity with id " + id + " is active");
        } else {
            // case set active from true -> false
            this.active = false;
            COUNTER--;
            System.out.println("StudentEntity with id " + id + " is disable");
        }
    }
}

package my.java.vlong.homework1;

public class StudentMain {
    public static void main(String[] args) {
        try {
            Student student1 = Student.createStudent();
            Student student2 = Student.createStudent();
            Student student3 = Student.createStudent();
            //            StudentEntity student4 = StudentEntity.createStudent();

            // we will disable student1 and add student4
            student1.setActive(false);
            Student student4 = Student.createStudent();

            // try enable student 1 and throw exception
            //            student1.setActive(true);

            // we will disable student4 and reactive student1
            student4.setActive(false);
            student1.setActive(true);

            // we will try active student1 when student1 is active and nothing happen
            student1.setActive(true);

        } catch (StudentCreatedException e) {
            System.out.println(e.getMessage());
        } catch (StudentActivedException e) {
            System.out.println(e.getMessage());
        }
    }
}

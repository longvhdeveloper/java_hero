package my.java.vlong.homework3_refactor.infrastructure;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.java.vlong.homework3_refactor.entity.Course;
import my.java.vlong.homework3_refactor.entity.Gender;
import my.java.vlong.homework3_refactor.utils.Database;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.repository.IStudentRepository;

public class StudentRepositoryImplDB implements IStudentRepository {

    private PreparedStatement preparedStatement;

    @Override
    public List<Student> findByNameContaining(String keyWord) {

        List<Student> students = new ArrayList<>();
        if (keyWord == null || keyWord.equals("")) {
            return students;
        }

        try {
            ResultSet resultSet = null;
            Connection connection = null;

            connection = Database.getConnection();
            String sql = "SELECT * FROM student LEFT JOIN course ON student.course_id = course.id WHERE id LIKE ? OR "
                    + "fullname LIKE ? ORDER BY student.id ASC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyWord + "%");
            preparedStatement.setString(2, "%" + keyWord + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String name = resultSet.getString("student.fullname");
                Date dateOfBirth = resultSet.getDate("student.date_of_birth");
                Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                Course course = new Course();
                course.setId(resultSet.getInt("course.id"));
                course.setName(resultSet.getString("course.name"));

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setDateOfBirth(dateOfBirth);
                student.setGender(gender);
                student.setCourse(course);

                students.add(student);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

    @Override
    public Optional<Student> add(Student t) {
        int count = 0;
        Connection connection = null;
        try {
            connection = my.java.vlong.homework3_refactor.utils.Database.getConnection();
            String sql = "INSERT INTO studentEntity(fullname, date_of_birth, gender, course_id) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setDate(2, new Date(t.getDateOfBirth().getTime()));
            preparedStatement.setInt(3, t.getGender().getCode());
            preparedStatement.setInt(4, t.getCourse().getId());
            if (count > 0) {
                return Optional.of(t);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Database.close(null, preparedStatement, connection);
            } catch (SQLException ex) {
                Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Student> update(Student t) {
        int count = 0;
        Connection connection = null;
        try {
            connection = Database.getConnection();
            String sql = "UPDATE studentEntity SET fullname = ?, date_of_birth = ?, gender = ?, course_id = ? WHERE id "
                    + "= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setDate(2, new Date(t.getDateOfBirth().getTime()));
            preparedStatement.setInt(3, t.getGender().getCode());
            preparedStatement.setInt(4, t.getCourse().getId());
            preparedStatement.setInt(5, t.getId());

            count = preparedStatement.executeUpdate();
            if (count > 0) {
                return Optional.of(t);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Optional<Student> t) {
        if (!t.isPresent()) {
            return false;
        }

        Student student = t.get();
        Connection connection = null;
        int count = 0;
        try {
            connection = Database.getConnection();
            String sql = "DELETE FROM studentEntity WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            count = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count > 0;
    }

    @Override
    public Optional<Student> findByOne(Integer k) {
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = Database.getConnection();
            String sql = "SELECT * FROM student LEFT JOIN course ON student.course_id = course.id WHERE student"
                    + ".id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, k);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String name = resultSet.getString("student.fullname");
                Date dateOfBirth = resultSet.getDate("student.date_of_birth");
                Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                Course course = new Course();
                course.setId(resultSet.getInt("course.id"));
                course.setName(resultSet.getString("course.name"));

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setDateOfBirth(dateOfBirth);
                student.setGender(gender);
                student.setCourse(course);

                return Optional.of(student);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = Database.getConnection();
            String sql = "SELECT * FROM student LEFT JOIN course ON student.course_id = course.id ORDER BY student.id"
                    + " ASC";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String name = resultSet.getString("student.fullname");
                Date dateOfBirth = resultSet.getDate("student.date_of_birth");
                Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                Course course = new Course();
                course.setId(resultSet.getInt("course.id"));
                course.setName(resultSet.getString("course.name"));

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setDateOfBirth(dateOfBirth);
                student.setGender(gender);
                student.setCourse(course);

                students.add(student);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

}

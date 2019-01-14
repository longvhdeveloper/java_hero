package my.java.vlong.homework3_refactor.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.java.vlong.homework3_refactor.entity.Course;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.repository.ICourseRepository;
import my.java.vlong.homework3_refactor.utils.Database;

public class CourseRepositoryImplDB implements ICourseRepository {

    private PreparedStatement preparedStatement;

    @Override
    public List<Student> getStudentsOfCourse(Optional<Course> course) {
        List<Student> students = new ArrayList<>();
        if (!course.isPresent()) {
            return students;
        }
        
        return null;
    }

    @Override
    public List<Course> findByNameContaining(String keyWord) {
        List<Course> courses = new ArrayList<>();
        if (keyWord == null || keyWord.equals("")) {
            return courses;
        }

        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Database.getConnection();

            String sql = "SELECT * FROM course WHERE id LIKE ? OR name LIKE ? ORDER BY id ASC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyWord + "%");
            preparedStatement.setString(2, "%" + keyWord + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));

                courses.add(course);
            }

            return courses;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    @Override
    public Optional<Course> add(Course t) {
        int count = 0;
        Connection connection = null;
        try {
            connection = Database.getConnection();
            String sql = "INSERT INTO course(name) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            count = preparedStatement.executeUpdate();
            if (count > 0) {
                return Optional.of(t);
            }
        } catch (ClassNotFoundException | SQLException ex) {
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
    public Optional<Course> update(Course t) {
        int count = 0;
        Connection connection = null;
        try {
            connection = Database.getConnection();
            String sql = "UPDATE course SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getId());
            count = preparedStatement.executeUpdate();
            if (count > 0) {
                return Optional.of(t);
            }
        } catch (ClassNotFoundException | SQLException ex) {
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
    public boolean delete(Optional<Course> t) {
        if (!t.isPresent()) {
            return false;
        }
        Course course = t.get();
        int count = 0;
        Connection connection = null;
        try {

            connection = Database.getConnection();
            String sql = "DELETE FROM course WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, course.getId());
            count = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Database.close(null, preparedStatement, connection);
            } catch (SQLException ex) {
                Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return count > 0;
    }

    @Override
    public Optional<Course> findByOne(Integer k) {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Database.getConnection();
            String sql = "SELECT * FROM course WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, k);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                return Optional.of(course);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Database.close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = Database.getConnection();
            String sql = "SELECT * FROM course ORDER BY id ASC";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));

                courses.add(course);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Database.close(resultSet, preparedStatement, connection);
            } catch (SQLException ex) {
                Logger.getLogger(CourseRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }

}

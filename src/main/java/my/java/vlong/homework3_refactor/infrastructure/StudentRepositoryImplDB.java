package my.java.vlong.homework3_refactor.infrastructure;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.java.vlong.homework3_refactor.utils.Database;
import my.java.vlong.homework3_refactor.entity.Student;
import my.java.vlong.homework3_refactor.repository.IStudentRepository;

public class StudentRepositoryImplDB implements IStudentRepository {

    private PreparedStatement preparedStatement;

    @Override
    public List<Student> findByNameContaining(String keyWord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentRepositoryImplDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Optional<Student> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Student> findByOne(Integer k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

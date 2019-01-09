package my.java.vlong.homework3.model;

import my.java.vlong.homework3.database.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel implements IStudent {
    private PreparedStatement preparedStatement;

    @Override
    public boolean addStudent(StudentEntity studentEntity) {
        int count = 0;
        if (studentEntity != null) {
            try {
                String sql = "INSERT INTO studentEntity(fullname, date_of_birth, gender, course_id) VALUES(?,?,?,?)";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, studentEntity.getFullName());
                preparedStatement.setDate(2, new Date(studentEntity.getDateOfBirth().getTime()));
                preparedStatement.setInt(3, studentEntity.getGender().getCode());
                preparedStatement.setInt(4, studentEntity.getCourseEntity().getId());

                count = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Database.close();
            }
        }
        return count > 0;
    }

    @Override
    public boolean updateStudent(StudentEntity studentEntity) {
        int count = 0;

        if (studentEntity != null) {
            try {
                String sql = "UPDATE studentEntity SET fullname = ?, date_of_birth = ?, gender = ?, course_id = ? WHERE id " +
                        "= ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, studentEntity.getFullName());
                preparedStatement.setDate(2, new Date(studentEntity.getDateOfBirth().getTime()));
                preparedStatement.setInt(3, studentEntity.getGender().getCode());
                preparedStatement.setInt(4, studentEntity.getCourseEntity().getId());
                preparedStatement.setInt(5, studentEntity.getId());

                count = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Database.close();
            }
        }

        return count > 0;
    }

    @Override
    public StudentEntity getStudent(int id) {
        ResultSet resultSet = null;
        if (id > 0) {
            try {
                String sql = "SELECT * FROM student LEFT JOIN course ON student.course_id = course.id WHERE student" +
                        ".id = ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getInt("student.id");
                    String fullName = resultSet.getString("student.fullname");
                    java.util.Date dateOfBirth = new java.util.Date(resultSet.getDate("student.date_of_birth").getTime());
                    Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                    CourseEntity courseEntity = new CourseEntity(resultSet.getInt("courseEntity.id"), resultSet.getString("courseEntity.name"));
                    return new StudentEntity(id, fullName, dateOfBirth, gender, courseEntity);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Database.close();
            }
        }
        return null;
    }

    @Override
    public List<StudentEntity> search(String keyword) {
        if (keyword.equals("")) {
            return null;
        }

        List<StudentEntity> searchResults = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM student LEFT JOIN course ON student.course_id = course.id WHERE id LIKE ? OR " +
                    "fullname LIKE ? ORDER BY student.id ASC";
            preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String fullName = resultSet.getString("student.fullname");
                java.util.Date dateOfBirth = new java.util.Date(resultSet.getDate("student.date_of_birth").getTime());
                Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                CourseEntity courseEntity = new CourseEntity(resultSet.getInt("courseEntity.id"), resultSet.getString("courseEntity.name"));

                searchResults.add(new StudentEntity(id, fullName, dateOfBirth, gender, courseEntity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Database.close();
        }

        return searchResults;
    }

    @Override
    public List<StudentEntity> getStudents() {
        List<StudentEntity> searchResults = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM student LEFT JOIN course ON student.course_id = course.id ORDER BY student.id" +
                    " ASC";
            preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String fullName = resultSet.getString("student.fullname");
                java.util.Date dateOfBirth = new java.util.Date(resultSet.getDate("student.date_of_birth").getTime());
                Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                CourseEntity courseEntity = new CourseEntity(resultSet.getInt("courseEntity.id"), resultSet.getString("courseEntity.name"));

                searchResults.add(new StudentEntity(id, fullName, dateOfBirth, gender, courseEntity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Database.close();
        }

        return searchResults;
    }

    @Override
    public boolean deleteStudent(StudentEntity studentEntity) {
        int count = 0;
        if (studentEntity != null) {
            try {
                String sql = "DELETE FROM studentEntity WHERE id = ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, studentEntity.getId());
                count = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Database.close();
            }
        }
        return count > 0;
    }

    @Override
    public List<StudentEntity> getStudentByCourse(CourseEntity courseEntity) {
        if (courseEntity == null) {
            return null;
        }

        List<StudentEntity> searchResults = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM student LEFT JOIN courseEntity ON student.course_id = courseEntity.id WHERE courseEntity.id=? " +
                    "ORDER " +
                    "BY student.id ASC";
            preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, courseEntity.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String fullName = resultSet.getString("student.fullname");
                java.util.Date dateOfBirth = new java.util.Date(resultSet.getDate("student.date_of_birth").getTime());
                Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                searchResults.add(new StudentEntity(id, fullName, dateOfBirth, gender, courseEntity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Database.close();
        }

        return searchResults;
    }
}

package my.java.vlong.homework3.controller;

import my.java.vlong.homework3.database.Database;
import my.java.vlong.homework3.model.Course;
import my.java.vlong.homework3.model.Gender;
import my.java.vlong.homework3.model.IStudent;
import my.java.vlong.homework3.model.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManagement implements IStudent {

    private PreparedStatement preparedStatement;
    private CourseManagement courseManagement;

    public StudentManagement() {
        this.courseManagement = new CourseManagement();
    }

    @Override
    public boolean addStudent(Student student) {
        int count = 0;
        if (student != null) {
            try {
                String sql = "INSERT INTO student(fullname, date_of_birth, gender, course_id) VALUES(?,?,?,?)";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, student.getFullName());
                preparedStatement.setDate(2, new Date(student.getDateOfBirth().getTime()));
                preparedStatement.setInt(3, student.getGender().getCode());
                preparedStatement.setInt(4, student.getCourse().getId());

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
    public boolean updateStudent(Student student) {
        int count = 0;

        if (student != null) {
            try {
                String sql = "UPDATE student SET fullname = ?, date_of_birth = ?, gender = ?, course_id = ? WHERE id " +
                        "= ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, student.getFullName());
                preparedStatement.setDate(2, new Date(student.getDateOfBirth().getTime()));
                preparedStatement.setInt(3, student.getGender().getCode());
                preparedStatement.setInt(4, student.getCourse().getId());
                preparedStatement.setInt(5, student.getId());

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
    public Student getStudent(int id) {
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
                    Course course = new Course(resultSet.getInt("course.id"), resultSet.getString("course.name"));
                    return new Student(id, fullName, dateOfBirth, gender, course);
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
    public List<Student> search(String keyword) {
        if (keyword.equals("")) {
            return null;
        }

        List<Student> searchResults = new ArrayList<>();
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
                Course course = new Course(resultSet.getInt("course.id"), resultSet.getString("course.name"));

                searchResults.add(new Student(id, fullName, dateOfBirth, gender, course));
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
    public List<Student> getStudents() {
        List<Student> searchResults = new ArrayList<>();
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
                Course course = new Course(resultSet.getInt("course.id"), resultSet.getString("course.name"));

                searchResults.add(new Student(id, fullName, dateOfBirth, gender, course));
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
    public boolean deleteStudent(Student student) {
        int count = 0;
        if (student != null) {
            try {
                String sql = "DELETE FROM student WHERE id = ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, student.getId());
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
    public List<Student> getStudentByCourse(Course course) {
        if (course == null) {
            return null;
        }

        List<Student> searchResults = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM student LEFT JOIN course ON student.course_id = course.id WHERE course.id=? " +
                    "ORDER " +
                    "BY student.id ASC";
            preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, course.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String fullName = resultSet.getString("student.fullname");
                java.util.Date dateOfBirth = new java.util.Date(resultSet.getDate("student.date_of_birth").getTime());
                Gender gender = Gender.valueOf(resultSet.getInt("student.gender"));
                searchResults.add(new Student(id, fullName, dateOfBirth, gender, course));
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

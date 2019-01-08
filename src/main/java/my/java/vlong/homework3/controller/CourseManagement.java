package my.java.vlong.homework3.controller;

import my.java.vlong.homework3.database.Database;
import my.java.vlong.homework3.model.Course;
import my.java.vlong.homework3.model.ICourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement implements ICourse {
    private PreparedStatement preparedStatement;

    @Override
    public boolean addCourse(Course course) {
        int count = 0;
        if (course != null) {
            try {
                String sql = "INSERT INTO course(name) VALUES (?)";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, course.getName());
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
    public boolean updateCourse(Course course) {
        int count = 0;
        if (course != null) {
            try {
                String sql = "UPDATE course SET name = ? WHERE id = ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, course.getName());
                preparedStatement.setInt(2, course.getId());
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
    public Course getCourse(int id) {
        if (id > 0) {
            ResultSet resultSet = null;
            try {
                String sql = "SELECT * FROM course WHERE id=?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new Course(resultSet.getInt("id"), resultSet.getString("name"));
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
    public List<Course> getCourses() {
        List<Course> searchResults = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM course ORDER BY id ASC";
            preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                searchResults.add(new Course(id, name));
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
    public List<Course> search(String keyword) {
        if (keyword.equals("")) {
            return null;
        }

        List<Course> searchResults = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM course WHERE id LIKE ? OR name LIKE ? ORDER BY id ASC";
            preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                searchResults.add(new Course(id, name));
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
    public boolean deleteCourse(Course course) {
        int count = 0;
        if (course != null) {
            try {
                String sql = "DELETE FROM course WHERE id = ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, course.getId());
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
}

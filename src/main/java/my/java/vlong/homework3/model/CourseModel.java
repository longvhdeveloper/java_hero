package my.java.vlong.homework3.model;

import my.java.vlong.homework3.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseModel implements ICourse {
    private PreparedStatement preparedStatement;

    @Override
    public boolean addCourse(CourseEntity courseEntity) {
        int count = 0;
        if (courseEntity != null) {
            try {
                String sql = "INSERT INTO courseEntity(name) VALUES (?)";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, courseEntity.getName());
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
    public boolean updateCourse(CourseEntity courseEntity) {
        int count = 0;
        if (courseEntity != null) {
            try {
                String sql = "UPDATE courseEntity SET name = ? WHERE id = ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, courseEntity.getName());
                preparedStatement.setInt(2, courseEntity.getId());
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
    public CourseEntity getCourse(int id) {
        if (id > 0) {
            ResultSet resultSet = null;
            try {
                String sql = "SELECT * FROM course WHERE id=?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new CourseEntity(resultSet.getInt("id"), resultSet.getString("name"));
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
    public List<CourseEntity> getCourses() {
        List<CourseEntity> searchResults = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM course ORDER BY id ASC";
            preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                searchResults.add(new CourseEntity(id, name));
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
    public List<CourseEntity> search(String keyword) {
        if (keyword.equals("")) {
            return null;
        }

        List<CourseEntity> searchResults = new ArrayList<>();
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

                searchResults.add(new CourseEntity(id, name));
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
    public boolean deleteCourse(CourseEntity courseEntity) {
        int count = 0;
        if (courseEntity != null) {
            try {
                String sql = "DELETE FROM courseEntity WHERE id = ?";
                preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, courseEntity.getId());
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

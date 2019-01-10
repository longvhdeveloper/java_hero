package my.java.vlong.homework3_refactor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static final String DATABASE_NAME = "schools";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String PORT = "3306";
    private static final String HOST = "localhost";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASE_NAME);
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws SQLException {
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }

        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }

        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

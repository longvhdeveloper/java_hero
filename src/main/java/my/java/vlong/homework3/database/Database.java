package my.java.vlong.homework3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private Connection connection;
    private String url;
    private String user;
    private String password;
    private String host;
    private int port;
    private String databaseName;

    private Database() throws SQLException {
        this.user = "root";
        this.password = "";
        this.host = "localhost";
        this.port = 3306;
        this.databaseName = "schools";

        this.url = String.format("jdbc:mysql://%s:%d/%s", host, port, databaseName);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database();

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance;
    }

    public static void close() {
        try {
            if (!instance.getConnection().isClosed()) {
                instance.getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

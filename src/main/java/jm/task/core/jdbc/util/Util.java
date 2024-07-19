package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "My24SQL";

    public void createConnection() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        createConnection();
        return connection;
    }
}

package org.shittystuff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// using singleton pattern to prevent multiple connection
public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            String url = "jdbc:mysql://localhost:3306/railway";
            String user = "root";
            String password = "2003";
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
}

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/railway";
        String user = "root";
        String password = "2003";
        return DriverManager.getConnection(url, user, password);
    }
}

package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
    private Statement statement;
    private static JDBCConnection jdbcConnection;
    public static JDBCConnection getInstance() {
        if (jdbcConnection == null) jdbcConnection = new JDBCConnection();
        return jdbcConnection;
    }
    public Statement jdbcConnection() {
        Connection connection = null;
        try {
            if (statement == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight?useSSL=false", "root",
                        "James124@");
                statement = connection.createStatement();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (SQLException s) {
            System.out.println("Database not Connected");
        }
        return statement;
    }
}

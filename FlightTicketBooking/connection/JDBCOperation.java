package connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCOperation {
    private Statement statement;
    private static JDBCOperation jdbcOperation;
    public static JDBCOperation getInstance() {
        if (jdbcOperation == null) jdbcOperation = new JDBCOperation();
        return jdbcOperation;
    }
    public ResultSet retriveQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public int updateQuery(String query) {
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public JDBCOperation() {
        statement = JDBCConnection.getInstance().jdbcConnection();
    }
}

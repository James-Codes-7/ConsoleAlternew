package librarymanagement.databaseconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCOperation {

    private static JDBCConnectionLibrary jdbcConnection;
    private ResultSet resultSet;
    private Statement statement;
    private int rowAffected;
    private static JDBCOperation jdbcOperation;

    public static JDBCOperation getInstance() {
        if (jdbcOperation == null) jdbcOperation = new JDBCOperation();
        return jdbcOperation;
    }
    public ResultSet retriveQuery(String query) {
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }
    public int updateQuery(String query) {
        try {
            rowAffected = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowAffected;
    }

    public JDBCOperation() {
        statement = JDBCConnectionLibrary.getInstance().jdbcConnection();
    }


}

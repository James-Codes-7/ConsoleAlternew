package schoolattendancealter.connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCOperations {

    private JDBCConnection jdbcConnection;
    private static JDBCOperations jdbcOperations;

    public static JDBCOperations getInstance()
    {
        if(jdbcOperations==null)jdbcOperations=new JDBCOperations();
        return jdbcOperations;
    }

    public ResultSet retriveQuery(String query)
    {
        try {
            return jdbcConnection.dataBaseConnection().executeQuery(query);
        }catch (SQLException e)
        {
            System.out.println("Cannot execute the query");
        }
        return null;
    }
    public int updateQuery(String query)
    {
        try
        {
            return jdbcConnection.dataBaseConnection().executeUpdate(query);
        }catch (SQLException e)
        {
            System.out.println("Cannot execute the query");
        }
        return 0;
    }
    public JDBCOperations()
    {
        jdbcConnection=JDBCConnection.getInstance();
    }
}

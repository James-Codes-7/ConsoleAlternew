package librarymanagement.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCConnectionLibrary {
    private static Statement statement;
    private static JDBCConnectionLibrary jdbcConnection;

    public static JDBCConnectionLibrary getInstance()
    {
        if(jdbcConnection==null)jdbcConnection=new JDBCConnectionLibrary();
        return jdbcConnection;
    }
    public Statement jdbcConnection()  {
        try
        {
            if(statement==null)
            {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql:"
                            + "//localhost:3306/library?useSSL=false",
                    "root","James124@");
            statement=connection.createStatement();}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return statement;
    }
}

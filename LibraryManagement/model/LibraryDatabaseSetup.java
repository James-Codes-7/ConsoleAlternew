package librarymanagement.model;

import librarymanagement.databaseconnection.JDBCOperation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryDatabaseSetup {

    private static LibraryDatabaseSetup libraryDatabaseSetup;
    private LibraryDatabase libraryDatabase;
    private JDBCOperation jdbcOperation;

    public void librarySetup()
    {
        libraryDatabase.setAdmin(12345,"admin");
        ResultSet resultSet=jdbcOperation.retriveQuery("select * from booklist");
        try {
            while (resultSet.next()) {
              Book book=new Book();
              book.setBookId(resultSet.getInt(1));
              book.setBookName(resultSet.getString(2));
              book.setBookAuther(resultSet.getString(3));
              book.setBookStack(resultSet.getInt(4));
              book.setBookrate(resultSet.getInt(5));
              libraryDatabase.setBook(book);
            }

            resultSet=jdbcOperation.retriveQuery("select * from libraryuser");
            while (resultSet.next())
            {
                User user =new User();
                user.setUserId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setMobileno(resultSet.getLong(3));
                libraryDatabase.setUserId(resultSet.getInt(1));
                libraryDatabase.setUser(user);
            }
            resultSet=jdbcOperation.retriveQuery("select * from bookassignlist");
            while (resultSet.next())
            {
                BookTransaction bookTransaction=new BookTransaction();
                bookTransaction.setBookId(resultSet.getInt(1));
                bookTransaction.setUserId(resultSet.getInt(2));
                bookTransaction.setBookAssaignDate(resultSet.getString(3));
                bookTransaction.setBookReturnDate(resultSet.getString(4));
                bookTransaction.setDamageFine(resultSet.getInt(5));
                bookTransaction.setBookStatus(resultSet.getString(6));
                libraryDatabase.setBookTransactions(bookTransaction);
            }
            System.out.println("Library Setuped Successfuly");

        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static LibraryDatabaseSetup getInstance()
    {
        if(libraryDatabaseSetup==null)libraryDatabaseSetup=new LibraryDatabaseSetup();
        return libraryDatabaseSetup;
    }
    private LibraryDatabaseSetup()
    {
        libraryDatabase=LibraryDatabase.getInstance();
        jdbcOperation=JDBCOperation.getInstance();
    }
}

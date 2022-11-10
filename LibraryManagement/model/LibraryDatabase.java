package librarymanagement.model;

import librarymanagement.databaseconnection.JDBCOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryDatabase {

   private  List<Book> book;
    private List<User> user;
    private List<BookTransaction> bookTransactions;
    private HashMap<Integer,String> adminCheck;
    private JDBCOperation jdbcOperation;

    private ArrayList<Integer> userId;

    public ArrayList<Integer> getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId.add(userId);
    }

    private static  LibraryDatabase libraryDatabase;

    public HashMap<Integer, String> getAdmin() {
        return adminCheck;
    }

    public void setAdmin(Integer adminId, String adminPassword) {
        this.adminCheck.putIfAbsent(adminId,adminPassword);
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book.add(book);
    }

    public List<BookTransaction> getBookTransactions() {
        return bookTransactions;
    }

    public void setBookTransactions(BookTransaction bookTransactions) {
        this.bookTransactions.add(bookTransactions);
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.add(user);
    }

    public void deleteBookInDatabase(int bookId)
    {
        int count=jdbcOperation.updateQuery("delete from booklist where bookid="+bookId);
    }
    public void addBookQuentityInDatabase(Book book)
    {
        int count = jdbcOperation.updateQuery("update booklist set stack=" +book.getBookStack()
                + " where bookid=" + book.getBookId());
    }

    public void bookAddUpdateInDatabase(Book book)
    {
        int count = jdbcOperation.updateQuery("insert into booklist values(" + book.getBookId() + ",'" + book.getBookName() + "','"
                + book.getBookAuther() + "'," + book.getBookStack() + "," + book.getBookrate() + ")");
    }
    public void bookAssignUpdateInDatabase(BookTransaction bookTransaction)
    {
        int count =jdbcOperation.updateQuery("insert into bookassignlist(bookid,userid,bookassigndate)"
                + " values(" + bookTransaction.getBookId() + "," + bookTransaction.getUserId() + ",'" +bookTransaction.getBookAssaignDate()+ "')");
    }
    public void userAddUpdateInDatabase(User user)
    {
        int count=jdbcOperation.updateQuery("insert into libraryuser values("+user.getUserId()+",'"
                + user.getUserName()+"',"+user.getMobileno()+")");
    }
    public void bookReturnAssignInDatabase(BookTransaction bookTransaction)
    {
        int count=jdbcOperation.updateQuery("update bookassignList set returndate ='"+bookTransaction.getBookReturnDate()+"'," +
                "damagefine="+bookTransaction.getDamageFine()+",bookstatus='"+bookTransaction.getBookStatus()+"' " +
                "where bookid="+bookTransaction.getBookId()+" and  userid="+bookTransaction.getUserId());
    }
    private LibraryDatabase()
    {
        book=new ArrayList<>();
        user=new ArrayList<>();
        bookTransactions=new ArrayList<>();
        user=new ArrayList<>();
        adminCheck=new HashMap<>();
        userId=new ArrayList<>();
        jdbcOperation=JDBCOperation.getInstance();
    }
    public  static LibraryDatabase getInstance()
    {
        if(libraryDatabase==null)libraryDatabase=new LibraryDatabase();
        return libraryDatabase;
    }

}


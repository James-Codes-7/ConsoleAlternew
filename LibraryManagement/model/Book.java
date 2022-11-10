package librarymanagement.model;

public class Book {
    int bookId;
    String bookName;
    int bookStack;
    int bookrate;
    String bookAuther;

    public String getBookAuther() {
        return bookAuther;
    }

    public void setBookAuther(String bookAuther) {
        this.bookAuther = bookAuther;
    }

    public int getBookrate() {
        return bookrate;
    }

    public void setBookrate(int bookrate) {
        this.bookrate = bookrate;
    }

    public int getBookStack() {
        return bookStack;
    }

    public void setBookStack(int bookStack) {
        this.bookStack = bookStack;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}

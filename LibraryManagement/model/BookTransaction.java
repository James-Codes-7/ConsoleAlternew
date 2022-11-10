package librarymanagement.model;

public class BookTransaction {
    int bookId;
    int userId;
    String bookAssaignDate;
    String bookReturnDate;
    int damageFine;
    String bookStatus;

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getDamageFine() {
        return damageFine;
    }

    public void setDamageFine(int damageFine) {
        this.damageFine = damageFine;
    }

    public String getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(String bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public String getBookAssaignDate() {
        return bookAssaignDate;
    }

    public void setBookAssaignDate(String bookAssaignDate) {
        this.bookAssaignDate = bookAssaignDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }



}

package librarymanagement.controler;

import librarymanagement.model.Book;
import librarymanagement.model.LibraryDatabase;

import java.util.ArrayList;
import java.util.List;

public class Checklist {
    private static Checklist checklist;
    public LibraryDatabase libraryDatabase;
    private int bookIdStart = 1001;
    public boolean bookIdCheck(int bookId) {
        List<Book> bookList = libraryDatabase.getBook();
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {

                return true;
            }
        }
        return false;
    }
    public int bookAutherCheck(String bookName, String autherName) {
        List<Book> bookList = libraryDatabase.getBook();
        for (Book book : bookList) {
            if (book.getBookName().equals(bookName) && book.getBookAuther().equals(autherName)) {
                return book.getBookId();
            }
        }
        return bookList.size() + bookIdStart;
    }

    public boolean userIdCheck(int userId) {
        ArrayList<Integer> userid = libraryDatabase.getUserId();
        for (Integer user : userid) {
            if (user == userId) {
                System.out.println("The user Already here");
                return true;
            }
        }
        return false;
    }

    public static Checklist getInstance() {
        if (checklist == null) checklist = new Checklist();
        return checklist;
    }

    private Checklist() {
        libraryDatabase = LibraryDatabase.getInstance();;
    }
}

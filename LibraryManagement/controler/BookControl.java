package librarymanagement.controler;

import librarymanagement.model.Book;
import librarymanagement.model.LibraryDatabase;
import librarymanagement.view.Display;
import librarymanagement.view.ManageBook;
import java.util.List;
import java.util.Scanner;

public class BookControl {
    private static BookControl bookControl;
    private Display display;
    private Checklist checklist;
    private int adminId;
    private String adminPassword;
    private int userStart = 10101;
    private int bookIdStart = 1001;
    private LibraryDatabase libraryDatabase;

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        int bookId = 0;
        display.alert("Enter the book Id");
        bookId = scanner.nextInt();
        scanner.nextLine();
        display.alert("Enter the Book Name");
        String bookName = scanner.nextLine();

        display.alert("Enter the Auther name");
        String autherName = scanner.nextLine();
        bookId = checklist.bookAutherCheck(bookName, autherName);
        display.alert("Enter the quatity");
        int quantity = scanner.nextInt();

        if (bookId > libraryDatabase.getBook().size() + bookIdStart - 1 || bookIdStart > bookId) {
            System.out.println();

            display.alert("Enter the book rate");
            int rate = scanner.nextInt();
            Book book = new Book();
            book.setBookId(bookId);
            book.setBookName(bookName);
            book.setBookAuther(autherName);
            book.setBookStack(quantity);
            book.setBookrate(rate);
            libraryDatabase.setBook(book);
        } else {
            libraryDatabase.getBook().get(bookId - bookIdStart).setBookStack(
                    libraryDatabase.getBook().get(bookId - bookIdStart).getBookStack() + quantity);
            libraryDatabase.addBookQuentityInDatabase(libraryDatabase.getBook().get(bookId - bookIdStart));

        }
        display.alert("Sucess Fully added\n");
    }

    public void deleteBook() {
        Scanner scan = new Scanner(System.in);
        display.alert("Enter the book Id");
        int bookId = scan.nextInt();
        if (checklist.bookIdCheck(bookId)) {
            libraryDatabase.deleteBookInDatabase(bookId);
            removeBook(bookId);
            display.alert("SuccessFully Deleted");
            return;
        }
        display.alert("Not Deleted");

    }

    private void removeBook(int bookId) {
        int count = 0;
        List<Book> bookList = libraryDatabase.getBook();
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                bookList.remove(count);
                return;
            }
            count++;
        }
    }

    public void showBook(boolean bool, int bookId) {
        List<Book> bookList = libraryDatabase.getBook();
        if (bool == true) {
            for (Book book : bookList) {
                display.alert("\nBook Id:        " + book.getBookId() + "\nBook Name:      " +
                        "" + book.getBookName() + "\nBook Auther:    " + book.getBookAuther()
                        + "\nBook Quentity:  " + book.getBookStack() + "\nBook Rate:      " + book.getBookrate());
            }
        } else {
            for (Book book : bookList) {
                if (book.getBookId() == bookId) {
                    display.alert("\nBook Id:        " + book.getBookId() + "\nBook Name:      " + book.getBookName() +
                            "\nBook Auther:    " + book.getBookAuther() + "\nBook Quentity:  " + book.getBookStack() +
                            "\nBook Rate:      " + book.getBookrate());
                }
            }
        }

    }

    public static BookControl getInstance() {
        if (bookControl == null) bookControl = new BookControl();
        return bookControl;
    }

    private BookControl() {
        libraryDatabase = LibraryDatabase.getInstance();
        checklist = Checklist.getInstance();
        display = Display.getInstance();
    }
}

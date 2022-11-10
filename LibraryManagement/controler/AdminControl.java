package librarymanagement.controler;

import librarymanagement.model.BookTransaction;
import librarymanagement.model.LibraryDatabase;
import librarymanagement.model.User;
import librarymanagement.view.Display;
import librarymanagement.view.ManageAdmin;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminControl {

    private static AdminControl adminControl;
    private Display display;
    private BookControl bookControl;
    private Checklist checklist;
    private LibraryDatabase libraryDatabase;
    private Scanner scanner = new Scanner(System.in);
    private int userStart = 10101;
    private int bookIdStart = 1001;

    public static AdminControl getInstance() {
        if (adminControl == null) adminControl = new AdminControl();
        return adminControl;
    }
    private AdminControl() {
        checklist = Checklist.getInstance();
        libraryDatabase = LibraryDatabase.getInstance();
        bookControl = BookControl.getInstance();
        display = Display.getInstance();
    }
    public void assignBookToUser() {
        int userId = 0, bookId = 0;
        ;

        try {
            display.alert("Enter the user Id");
            userId = scanner.nextInt();
            if (!checklist.userIdCheck(userId)) {
                display.alert("Cannot Identify user Id\n");
                return;
            }
            display.alert("Enter the Book Id");
            bookId = scanner.nextInt();
            if (!checklist.bookIdCheck(bookId)) {
                display.alert("Cannot Identify the book");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            assignBookToUser();
        }
        List<BookTransaction> bookTransactions = libraryDatabase.getBookTransactions();
        for (BookTransaction bookAssign : bookTransactions) {
            if (bookAssign.getUserId() == userId) {
                if (bookId == bookAssign.getBookId() && bookAssign.getBookStatus() == null) {
                    display.alert("You already have that book");
                    return;
                }
            }
        }
        LocalDate localDate = java.time.LocalDate.now();
        BookTransaction bookTransaction = new BookTransaction();
        bookTransaction.setBookId(bookId);
        bookTransaction.setUserId(userId);
        bookTransaction.setBookStatus(null);
        bookTransaction.setDamageFine(0);
        bookTransaction.setBookAssaignDate(localDate.toString());
        bookTransaction.setBookReturnDate("404");
        libraryDatabase.setBookTransactions(bookTransaction);
        libraryDatabase.bookAssignUpdateInDatabase(bookTransaction);
        display.alert("SuccessFully Assign");
    }

    public void returnTheBook() {
        Scanner scanner = new Scanner(System.in);
        int userIdCheck = 1, bookIdCheck = 1;
        int userId = 0;
        int bookId = 0;
        int bookRate = 0;
        String status = "";
        BookTransaction returnBookAdd = null;
        int fine = 0;
        boolean bool = true;
        label:
        while (bool) {

            try {
                display.alert("Enter the user Id");
                userId = scanner.nextInt();
                if (userId != libraryDatabase.getUserId().get(userId - userStart)) {
                    display.alert("Enter the correct user id");
                    continue;
                }
                display.alert("Enter the Book Id");
                bookId = scanner.nextInt();
                List<BookTransaction> bookTransactions = libraryDatabase.getBookTransactions();
                for (BookTransaction bookAssign : bookTransactions) {
                    if (bookAssign.getUserId() == userId) {
                        userIdCheck = 0;
                        if (bookId == bookAssign.getBookId() && bookAssign.getBookStatus() == null) {
                            bookIdCheck = 0;
                            bookRate = libraryDatabase.getBook().get(bookId - bookIdStart).getBookrate();
                            returnBookAdd = bookAssign;
                            break label;
                        } else {
                            display.alert("You already return that book");
                            return;
                        }
                    }
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                assignBookToUser();
            }
        }
        if (userIdCheck == 1) {
            display.alert("The user Does not have any book");
            return;
        }
        if (bookIdCheck == 1) {
            display.alert("The book id was Wrong");
            return;
        }

        byte option = 0;
        while (true) {
            try {
                display.alert("Enter the book status\n");
                System.out.println();
                display.alert("Good         Press 1");
                display.alert("Damage       Press 2");
                option = scanner.nextByte();
                if (option == 2) {
                    fine = bookRate / 2;
                    status = "damaged";
                } else {
                    fine = 0;
                    status = "Good";
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                this.scanner.next();
            }
        }

        LocalDate localDate = java.time.LocalDate.now();


        returnBookAdd.setBookReturnDate(localDate.toString());
        returnBookAdd.setDamageFine(fine);
        returnBookAdd.setBookStatus(status);
        libraryDatabase.bookReturnAssignInDatabase(returnBookAdd);
        display.alert("Book succesfully returned");
    }

    public void addUser() {
        try {
            Scanner scanner = new Scanner(System.in);
            display.alert("Enter the user Id");
            int userId = scanner.nextInt();
            if (checklist.userIdCheck(userId)) {
                display.alert("Cannot add ");
                return;
            }
            scanner.nextLine();
            display.alert("Enter the user name");
            String userName = scanner.nextLine();
            display.alert("Enter the user Mobile number");
            long mobileno = scanner.nextLong();
            User user = new User();
            user.setUserId(libraryDatabase.getUserId().size() + userStart);
            user.setMobileno(mobileno);
            user.setUserName(userName);
            libraryDatabase.setUserId(userId);
            libraryDatabase.setUser(user);
            libraryDatabase.userAddUpdateInDatabase(user);
            display.alert("SucessFully added");
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            addUser();
        }
    }

    public void viewBookAssignList() {
        List<BookTransaction> bookTransactionList = libraryDatabase.getBookTransactions();
        for (BookTransaction bookTransaction : bookTransactionList) {
            if (bookTransaction.getBookReturnDate() == "404") {
                display.alert("User Id:" + bookTransaction.getUserId());
                bookControl.showBook(false, bookTransaction.getBookId());
            }
        }
    }

    public void viewUser() {
        Scanner scan = new Scanner(System.in);
        display.alert("Enter the user Id");
        int userId = scan.nextInt();
        try {

            if (userId != libraryDatabase.getUserId().get(userId - userStart)) {
                display.alert("The Id is wrong");
                return;
            }
            User user = libraryDatabase.getUser().get(userId - userStart);
            display.alert("User ID:" + user.getUserId() + " User Name:" + user.getUserName() + " User phoneno:" + user.getMobileno());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewUserList() {
        List<User> userLists = libraryDatabase.getUser();
        for (User user : userLists) {
            display.alert("User ID:" + user.getUserId() + "\tUser Name:" + user.getUserName() + "\tUser phoneno:" + user.getMobileno());
            System.out.println();
        }
    }
}

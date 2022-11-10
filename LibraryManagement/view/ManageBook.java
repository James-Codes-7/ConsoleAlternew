package librarymanagement.view;

import librarymanagement.controler.BookControl;

import java.util.Scanner;

public class ManageBook {
    private static ManageBook manageBook;
    private BookControl bookControl;

    public static ManageBook getInstance() {
        if (manageBook == null) manageBook = new ManageBook();
        return manageBook;
    }
    public void bookSite() {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        byte option = 0;
        while (bool) {
            System.out.println("View Book List          Press 1");
            System.out.println("Add  book               Press 2");
            System.out.println("Delete the Book         Press 3");
            System.out.println("Exit                    Press 4");
            option = scan.nextByte();
            if (option == 4) {
                bool = false;
                return;
            }
            switch (option) {
                case 1:
                    bookControl.showBook(true, 0);
                    break;
                case 2:
                    bookControl.addBook();
                    break;
                case 3:
                    bookControl.deleteBook();
                    break;
                default:
                    System.out.println("Enter the valid one");
            }
        }
    }
    private ManageBook() {

        bookControl = BookControl.getInstance();
    }
}

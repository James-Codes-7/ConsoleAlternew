package librarymanagement.view;

import librarymanagement.model.LibraryDatabaseSetup;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Library {

    private ManageAdmin manageAdmin;
    private LibraryDatabaseSetup libraryDatabaseSetup;
    private ManageBook manageBook;
    private Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        Library library =new Library();
        library.libraryPage();

    }

    private void libraryPage()
    {
        libraryDatabaseSetup.librarySetup();
        boolean bool = true;
        byte option = 0;
        while (bool) {

            try {
                System.out.println("Manage Book                  Press 1");
                System.out.println("Manage User                  Press 2");
                System.out.println("Exit                         Press 3");
                option = scanner.nextByte();
                if (option == 3) {
                    break;
                }
                switch (option) {
                    case 1:
                        manageBook.bookSite();
                        break;
                    case 2:
                        manageAdmin.adminPage();
                        break;
                    default:
                        System.out.println("Enter the valid one");
                }
            }catch (InputMismatchException e)
            {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }
    }
    private Library()
    {
        manageAdmin=ManageAdmin.getInstance();
        manageBook=ManageBook.getInstance();
        libraryDatabaseSetup=LibraryDatabaseSetup.getInstance();
    }
}

package librarymanagement.view;

import librarymanagement.controler.AdminControl;
import librarymanagement.model.LibraryDatabase;

import java.util.Scanner;

public class ManageAdmin {

    private static ManageAdmin manageAdmin;
    private AdminControl adminControl;
    private LibraryDatabase libraryDatabase;
    public static ManageAdmin getInstance()
    {
        if(manageAdmin==null)manageAdmin=new ManageAdmin();
        return manageAdmin;
    }

    private Scanner scanner =new Scanner(System.in);
    public void adminPage() {
        Scanner scan=new Scanner(System.in);
        boolean bool=true;
        byte option=0;

        System.out.println("Enter the admin Id");
        int adminid=0;
        String password="";
        f1:while(bool)
        {
            adminid=scan.nextInt();
            while(libraryDatabase.getAdmin().containsKey(adminid))
            {
                System.out.println("Enter the password");
                password=scan.next();
                if(libraryDatabase.getAdmin().get(adminid).equals(password))
                {
                    System.out.println("Welcome to Admin Page");
                    bool=false;
                    break f1;
                }
                else System.out.println("Wrong Pssword");
            }
            System.out.println("Enter the correct Id");

        }
        System.out.println("-->Welcome Admin<--");
        bool=true;
        while(bool)
        {
            System.out.println("\nView User                   Press 1");
            System.out.println("Add User                    Press 2");
            System.out.println("View User List              Press 3");
            System.out.println("Assign Book User            Press 4");
            System.out.println("Return the book             Press 5");
            System.out.println("View the book Assign List   Press 6");
            System.out.println("Exit                        Press 7");
            System.out.println("Enter the option");
            option=scan.nextByte();
            if(option==7){bool=false;return;}
            switch (option)
            {
                case 1:adminControl.viewUser();
                    break;
                case 2:adminControl.addUser();
                    break;
                case 3:adminControl.viewUserList();
                    break;
                case 4:adminControl.assignBookToUser();
                    break;
                case 5:adminControl.returnTheBook();
                    break;
                case 6:adminControl.viewBookAssignList();
                break;
                default:
                    System.out.println("Enter the valid one");

            }
        }

    }
    private ManageAdmin()
    {
        adminControl=AdminControl.getInstance();
        libraryDatabase=LibraryDatabase.getInstance();
    }
}

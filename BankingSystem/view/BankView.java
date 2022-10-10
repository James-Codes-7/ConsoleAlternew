package bankingalter.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankView {
    private ManageCustomer manageCustomer;
  private   ManageAdmin manageAdmin;
    private Scanner scanner =new Scanner(System.in);
    public static void main(String[] args) {
        BankView bankView=new BankView();
        bankView.frontPage();

    }
    public  void frontPage()
    {
        System.out.println("Welcome to Banking System");
        boolean iterate=true;
        int option=0;
        while(iterate)
        {
            try {
                System.out.println("Admin       Press 1");
                System.out.println("Customer    Press 2");
                System.out.println("Exit        Press 3");
                System.out.println("Enter the option");
                option = scanner.nextInt();
                if (option == 3)break;
                switch (option) {
                    case 1:
                        manageAdmin.adminView();
                        break;
                    case 2:
                        manageCustomer.clientView();
                        break;
                    default:
                        System.out.println("Enter the correct one");
                        System.out.println();
                }
            }catch (InputMismatchException e)
            {
                System.out.println("Input Mismatch");
                scanner.next();
            }

        }
    }
    public BankView()
    {
      manageCustomer=ManageCustomer.getInstance();
      manageAdmin=ManageAdmin.getInstance();
    }
}

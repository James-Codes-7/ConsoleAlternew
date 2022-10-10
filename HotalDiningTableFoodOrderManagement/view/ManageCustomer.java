package hotalmanagementalter.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageCustomer {

    private static ManageCustomer manageCustomer;
    private TableView tableView;
    private  Scanner scanner =new Scanner(System.in);

    public static ManageCustomer getInstance()
    {
        if(manageCustomer==null)manageCustomer=new ManageCustomer();
        return manageCustomer;
    }
    public void customerView()
    {

        boolean bool=true;
        byte option=0;
        String tables[]= {"Tittan","wood","corner","tytus","party","Exit"};

        System.out.println();
        System.out.println("Select the table");
        System.out.println();
        while(bool) {
            try {
                for (int i = 0; i < tables.length; i++)
                    System.out.printf(tables[i] + "\t\t\tPress " + (i + 1) + "\n");
                System.out.println("Enter the option");
                option = scanner.nextByte();
                if (option == 6) {
                    return;
                }
                switch (option) {

                    case 1:
                        tableView.tableSelect(tables[option - 1]);
                        break;
                    case 2:
                        tableView.tableSelect(tables[option - 1]);
                        break;
                    case 3:
                        tableView.tableSelect(tables[option - 1]);
                        break;
                    case 4:
                        tableView.tableSelect(tables[option - 1]);
                        break;
                    case 5:
                        tableView.tableSelect(tables[option - 1]);
                        break;
                    default:
                        System.out.println("Enter the corectOne");

                }
            }catch (InputMismatchException e)
            {
                System.out.println("Input Mismatch");
                scanner.next();
            }
        }
    }
    public  ManageCustomer()
    {
        tableView=TableView.getInstance();
    }
}

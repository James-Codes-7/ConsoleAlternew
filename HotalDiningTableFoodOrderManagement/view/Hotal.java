package hotalmanagementalter.view;




import java.util.InputMismatchException;
import java.util.Scanner;

public class Hotal {
    private ManageCustomer manageCustomer;
    private ManageOwner manageOwner;
    private HotalSetup hotalSetup;
    public static void main(String[] args) {

        Hotal startHotal=new Hotal();

        startHotal.hotalPage();

    }

    public void hotalPage()
    {
        System.out.println("Welcome To Our Hotel");
        Scanner scan=new Scanner(System.in);
        hotalSetup.hotalSetup();
        boolean bool=true;
        byte option=0;
        System.out.println();
        while(bool)
        {
             try {
                 System.out.println();
                 System.out.println("Customer    Press 1");
                 System.out.println("Owner       Press 2");
                 System.out.println("Exit        Press 3");
                 System.out.println("Enter the option");
                 option = scan.nextByte();
                 if (option == 3) {
                     return;
                 }
                 switch (option) {
                     case 1:
                         manageCustomer.customerView();
                         break;
                     case 2:
                         manageOwner.OwnerView();
                         break;
                     default:
                         System.out.println("Enter the correct one");
                 }
             }catch (InputMismatchException e){
                 System.out.println("Input mismatch");scan.next();}

        }

    }
    public Hotal()
    {
        manageCustomer=ManageCustomer.getInstance();
        manageOwner=ManageOwner.getInstance();
        hotalSetup=HotalSetup.getInstance();
    }

}

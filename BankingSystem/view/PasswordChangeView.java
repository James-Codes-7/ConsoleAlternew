package bankingalter.view;

import bankingalter.controller.CheckList;
import bankingalter.controller.UserLoginControl;
import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;


import java.util.List;
import java.util.Scanner;

public class PasswordChangeView {
    private  static  PasswordChangeView passwordChangeView;
   private BankDatabase bankDatabase;

    private CheckList checkList;
    private Scanner scanner=new Scanner(System.in);
    private UserLoginControl userLoginControl;
    public void goToPasswordChange(long accountno) {
        System.out.println("Please Change your password");
        System.out.println();
        String phoneno = "";
        boolean bool = true;
        String password = "";
        System.out.println("Enter Your phone number");
        while (bool) {
            phoneno = scanner.next();
            while (userLoginControl.getPasswordChange().get(accountno).equals(phoneno)) {
                System.out.println("Enter the new password");
                password = scanner.next();
                if (checkList.passwordCheck(password)) {
                    passwordChanging(accountno, password);
                    System.out.println("Password Succesfully Changed");
                    return;
                } else {
                    System.out.println("Re enter Password");
                    System.out.println();
                }
                System.out.println("Enter the correct phone number");
            }
        }
    }
    private void passwordChanging (long accountno, String password)
    {
        List<Customer> customerDetails=bankDatabase.getCustomers();
        for (Customer changeProcess : customerDetails) {
            if (changeProcess.getAccountNo() == accountno) {
                changeProcess.setPassword(password);
                userLoginControl.setLoginCheck(accountno,password);
                return;
            }
        }
    }
    public PasswordChangeView()
    {

        userLoginControl=UserLoginControl.getInstance();
       checkList= CheckList.getInstance();
       bankDatabase=BankDatabase.getInstance();
    }

    public static PasswordChangeView getInstance()
    {
        if(passwordChangeView==null)passwordChangeView=new PasswordChangeView();
        return passwordChangeView;
    }
}

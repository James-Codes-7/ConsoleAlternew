package bankingalter.controller;

import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;

import java.util.List;
import java.util.regex.Pattern;

public class CheckList {

    private static  CheckList checkList;
    private UserLoginControl userLoginControl;
    private BankDatabase bankDatabase;
    private AdminLoginControl adminLoginControl;
    public  boolean nameCheck(String userName)
    {
        return Pattern.matches("[A-Z][^0-9]+", userName);
    }

    public  boolean mobileNumberCheck(String mobileno)
    {
        return Pattern.matches("[0-9]{10}", mobileno);
    }

    public boolean passwordCheck(String password)
    {
        return Pattern.matches("[^0-9][a-z,A-Z,0-9]{7,}", password);
    }

    public boolean adminPasswordCheck(long adminId,String adminPassword)
    {

        if(adminLoginControl.getAdminCheck().get(adminId).equals(adminPassword))
        {
            return true;
        }
        else System.out.println("Password Wrong");
        return false;


    }
    public  static  CheckList getInstance()
    {
        if(checkList==null)checkList=new CheckList();
        return checkList;
    }
    public boolean adminIdCheck(long adminId)
    {
        return adminLoginControl.getAdminCheck().containsKey(adminId) ;
    }

    public CheckList()
    {
       adminLoginControl=AdminLoginControl.getInstance();
       bankDatabase=BankDatabase.getInstance();
       userLoginControl=UserLoginControl.getInstance();
    }
    public boolean receiverAccountNumberCheck(long receiveraccountno)
    {
       return userLoginControl.getLoginCheck().containsKey(receiveraccountno);

    }
    public  boolean transferAmountCheck(long amount, long accountno)
    {
        List<Customer> customerDetails=bankDatabase.getCustomers();
        for(Customer amountCheck:customerDetails)
        {
            if(accountno==amountCheck.getAccountNo())
            {
                if(amount>amountCheck.getAccountBalance())
                {
                    System.out.println("Insufficient Balance");
                    return true;
                }
            }
        }
        return false;
    }

}

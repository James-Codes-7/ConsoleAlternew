package bankingalter.view;


import bankingalter.controller.UserLoginControl;
import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;


import java.util.HashMap;
import java.util.Scanner;

public class ManageCustomer {
    private static  ManageCustomer manageCustomer;

    private PasswordChangeView passwordChangeView;
    private AccountCreateView accountCreateView;
    private BankDatabase bankDatabase;
    private UserLoginControl userLoginControl;
    private TransactionView transactionView;
    private Scanner scanner=new Scanner(System.in);
    private  long  generateAccountNumber=1;
    private  int accountCreate()
    {

        String userName=accountCreateView.namePage();
        String password=accountCreateView.passwordPage();
        String mobileno=accountCreateView.mobileNoPage();
        long initialAmount=accountCreateView.initialAmountPage();
        System.out.println("Your Account number is:"+generateAccountNumber);
        HashMap<Long,String> loginCheck=new HashMap<>();
        loginCheck.put(generateAccountNumber,password);
        userLoginControl.setLoginCheck(loginCheck);
        HashMap<Long,String> passwordChangeReferance=new HashMap<>();
        passwordChangeReferance.put(generateAccountNumber,mobileno);
        userLoginControl.setPasswordChange(passwordChangeReferance);
        Customer customer=new Customer();
        customer.setAccountNo(generateAccountNumber++);
        customer.setUserName(userName);
        customer.setPassword(password);
        customer.setMobileNo(mobileno);
        customer.setAccountBalance(initialAmount);
        customer.setDate(java.time.LocalDate.now());
        bankDatabase.setCustomers(customer);
        System.out.println("Account Created SuccessFully");
        return 1;

    }
    public  void clientView()
    {
        boolean iterate=true;
        int check=0,option=0;

        while(iterate)
        {
            System.out.println("Account Create    Press 1");
            System.out.println("Login             Press 2");
            System.out.println("Exit              Press 3");
            System.out.println("Enter the option");
            option=scanner.nextInt();
            if(option==3)return;
            switch(option)
            {
                case 1:check=accountCreate();
                    break;
                case 2:if(check!=0)
                {customerLogin();
                    break;}
                default :System.out.println("Enter the correct option");
                    System.out.println();
            }

        }
    }
    private void customerLogin()
    {
        long accountNo = 1;
        String password = "";
        boolean bool = true;
        int limit=3;
        byte passwordwrongcheck = 0;
        System.out.println("Enter the Account number");
        f1:
        while (bool) {
            accountNo = scanner.nextLong();
            while (userLoginControl.getLoginCheck().containsKey(accountNo)) {
                System.out.println("Enter the correct password here");
                password = scanner.next();
                if (password.equals(userLoginControl.getLoginCheck().get(accountNo))) {
                    bool = false;
                    break f1;
                }
                passwordwrongcheck++;
                if (passwordwrongcheck == limit) {
                    passwordChangeView.goToPasswordChange(accountNo);
                    passwordwrongcheck = 0;
                }
            }
            System.out.println("Enter the correct account no");
        }
        transactionView.transferPage(accountNo);


    }
    public static ManageCustomer getInstance()
    {
        if(manageCustomer==null)manageCustomer=new ManageCustomer();
        return manageCustomer;
    }
    public ManageCustomer()
    {
        accountCreateView=AccountCreateView.getInstance();
        bankDatabase=BankDatabase.getInstance();
        userLoginControl=UserLoginControl.getInstance();
        passwordChangeView=PasswordChangeView.getInstance();
        transactionView=TransactionView.getInstance();

    }

}

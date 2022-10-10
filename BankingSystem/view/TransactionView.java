package bankingalter.view;

import bankingalter.controller.CheckList;
import bankingalter.model.AccountTransfer;
import bankingalter.model.BankDatabase;
import bankingalter.model.Customer;
import bankingalter.model.Deposit;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TransactionView {

    private static TransactionView transactionView;
    private BankDatabase bankDatabase;
    private CheckList checkList;
    private Scanner scanner =new Scanner(System.in);

    public static TransactionView getInstance()
    {
        if(transactionView==null)transactionView=new TransactionView();
        return transactionView;

    }
    private void balanceCheck(long accountno)
    {

        List<Customer> customerList=bankDatabase.getCustomers();
        for(Customer balanceView:customerList)
        {
            if(balanceView.getAccountNo()==accountno)
            {
                System.out.println("Your Account balance is:"+balanceView.getAccountBalance());
                System.out.println();
                return;
            }
        }
    }
    private  void depositMoney(long accountno) {
        long amount = 0;
        boolean bool = true;
        byte option = 0;
        System.out.println("Enter the amount");
        bool = true;
        while (bool) {
            try {
                amount = scanner.nextLong();
                System.out.println("If you want change amount Press 1 else 0");
                option = scanner.nextByte();
                if (option == 1) {
                    System.out.println("Re-Enter amount");
                    System.out.println();
                } else break;
            }catch (InputMismatchException e)
            {
                System.out.println("Input Mismatch");
                scanner.next();
            }
        }
        List<Customer> customerDetails=bankDatabase.getCustomers();
        Deposit deposit=new Deposit();
        for (Customer moneyAdd : customerDetails) {
            if (moneyAdd.getAccountNo() == accountno) {
                moneyAdd.setAccountBalance(moneyAdd.getAccountBalance()+amount);
                System.out.println("Amount is SuccessFully added");
                deposit.setSelfaccountno(accountno);
                deposit.setDepositAmount(amount);
                deposit.setDepositDate(java.time.LocalDate.now());
                deposit.setTransferType("Deposited");
                deposit.setSelfaccountBalance(moneyAdd.getAccountBalance());
                bankDatabase.setDeposits(deposit);
                System.out.println();
                return;
            }
        }
    }
    private  void accountTransfer(long accountno)
    {
        long amount=0;
        boolean bool=true;
        byte option=0;
        long receiverAccountNumber=0;
        System.out.println("Enter the Receive Customer Account no");

        while(bool)
        {

            receiverAccountNumber=scanner.nextLong();
            if(checkList.receiverAccountNumberCheck(receiverAccountNumber))
            {
                System.out.println("The receiver Account number:"+receiverAccountNumber);
                System.out.println("If you want change receiver Account No  Press 1 else 0");
                option=scanner.nextByte();
                if(option==1) {System.out.println("Re-Enter the accountno");continue;}
                else {bool=false;break;}
            }
            else System.out.println("Enter the correct receiver Account number");
        }
        System.out.println("Enter the Transfer Amount");
        bool=true;

        amount=scanner.nextLong();
        long withdrawBalance=0;
        long receiverbalance=0;
        if(!checkList.transferAmountCheck(amount,accountno))
        {
            List<Customer> customerDetails=bankDatabase.getCustomers();
            for(Customer moneyTransfer:customerDetails)
            {
                if(moneyTransfer.getAccountNo()==receiverAccountNumber)
                {
                    moneyTransfer.setAccountBalance(moneyTransfer.getAccountBalance()+amount);
                    receiverbalance=moneyTransfer.getAccountBalance();
                }
                if(moneyTransfer.getAccountNo()==accountno)
                {
                    moneyTransfer.setAccountBalance(moneyTransfer.getAccountBalance()-amount);
                    withdrawBalance=moneyTransfer.getAccountBalance();


                }
            }
        }
        else return;
        AccountTransfer accountTransfer=new AccountTransfer();
        accountTransfer.setUserAccountNo(accountno);
        accountTransfer.setReceiverAccountNo(receiverAccountNumber);
        accountTransfer.setTransferAmount(amount);
        accountTransfer.setTransferbalanceAmount(withdrawBalance);
        accountTransfer.setTransferDate(java.time.LocalDate.now());
        accountTransfer.setStatus("Transfered");
        bankDatabase.setAccountTransferList(accountTransfer);
       Deposit deposit=new Deposit();
       deposit.setSelfaccountno(receiverAccountNumber);
       deposit.setDepositAmount(amount);
       deposit.setDepositDate(java.time.LocalDate.now());
       deposit.setTransferType("Deposited");
       deposit.setSelfaccountBalance(receiverbalance);
       bankDatabase.setDeposits(deposit);
        System.out.println("Transactions successfullly completed");
        System.out.println();

    }
    private   void viewTransactionsHistory(long AccountNo)
    {
        System.out.println("Deposit History");
        System.out.println();

        List<Deposit> deposits=bankDatabase.getDeposits();
        for(Deposit depositeDetails:deposits)
        {
            if(AccountNo==depositeDetails.getSelfaccountno())
            {
                System.out.println("Account No:     "+depositeDetails.getSelfaccountno());
                System.out.println("Deposite Amount:"+depositeDetails.getDepositAmount());
                System.out.println("Deposite Time:  "+depositeDetails.getDepositDate());
                System.out.println("Account balance:"+depositeDetails.getSelfaccountBalance());
                System.out.println("Status:         "+depositeDetails.getTransferType());


            }
            System.out.println();
        }
        System.out.println("Account Transfer History");
        System.out.println();
        List<AccountTransfer> accountTransfer=bankDatabase.getAccountTransferList();
   for(AccountTransfer accountTransferHistory:accountTransfer)
        {
            if(AccountNo==accountTransferHistory.getUserAccountNo())
            {
                System.out.println("Account No:       "+accountTransferHistory.getUserAccountNo());
                System.out.println("ReceiverAccountNo:"+accountTransferHistory.getReceiverAccountNo());
                System.out.println("Transfer Amount:  "+accountTransferHistory.getTransferAmount());
                System.out.println("Withdraw Date:    "+accountTransferHistory.getTransferDate());
                System.out.println("Account balance:  "+accountTransferHistory.getTransferbalanceAmount());
                System.out.println("Status:           "+accountTransferHistory.getStatus());


            }
            System.out.println();
        }
    }
    public void transferPage(long accountNo)
    {
        boolean bool=true;
        byte option=0;
        while(bool)
        {


            System.out.println("View Account Balance      Press 1");
            System.out.println("Deposit Money             Press 2");
            System.out.println("Account Transfer          Press 3");
            System.out.println("Transactions History      Press 4");
            System.out.println("Exit                      Press 5");
            option=scanner.nextByte();
            switch(option)
            {
                case 1:balanceCheck(accountNo);
                    break;
                case 2:depositMoney(accountNo);
                    break;
                case 3:accountTransfer(accountNo);break;
                case 4:viewTransactionsHistory(accountNo);break;
                case 5:return;
            }
            System.out.println();

        }
    }
    public TransactionView()
    {
        bankDatabase=BankDatabase.getInstance();
        checkList=CheckList.getInstance();
    }
}

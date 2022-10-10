package bankingalter.model;

import java.util.ArrayList;
import java.util.List;

public class BankDatabase {

    private static BankDatabase bankDatabase;

    public static BankDatabase getInstance()
    {
        if(bankDatabase==null)bankDatabase=new BankDatabase();
        return bankDatabase;
    }
    public  BankDatabase()
    {
        accountTransferList=new ArrayList<>();
        customers=new ArrayList<>();
        deposits=new ArrayList<>();

    }


    private List<AccountTransfer> accountTransferList;

    private  List<Customer> customers;

    private List<Deposit> deposits;

    public List<AccountTransfer> getAccountTransferList() {
        return accountTransferList;
    }

    public void setAccountTransferList(List<AccountTransfer> accountTransferList) {
        this.accountTransferList = accountTransferList;
    }
    public void setAccountTransferList(AccountTransfer accountTransfer)
    {
        this.accountTransferList.add(accountTransfer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public void setCustomers(Customer customer)
    {
        this.customers.add(customer);
    }
    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }
    public void setDeposits(Deposit deposit)
    {
        this.deposits.add(deposit);
    }
}

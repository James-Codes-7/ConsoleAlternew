package bankingalter.model;

import java.time.LocalDate;

public class AccountTransfer {
    private long userAccountNo;
    private  long receiverAccountNo;
    private  long transferAmount;
    private long transferbalanceAmount;
    private String status;
    private LocalDate transferDate;

    public long getUserAccountNo() {
        return userAccountNo;
    }

    public void setUserAccountNo(long userAccountNo) {
        this.userAccountNo = userAccountNo;
    }

    public long getReceiverAccountNo() {
        return receiverAccountNo;
    }

    public void setReceiverAccountNo(long receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }

    public long getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(long transferAmount) {
        this.transferAmount = transferAmount;
    }

    public long getTransferbalanceAmount() {
        return transferbalanceAmount;
    }

    public void setTransferbalanceAmount(long transferbalanceAmount) {
        this.transferbalanceAmount = transferbalanceAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }
}

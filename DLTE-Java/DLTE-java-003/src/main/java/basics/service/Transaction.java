package basics.service;
import java.util.Scanner;
import java.util.Date;

public class Transaction {
    private Date transactionDate;
    private Integer transactionAmount;
    private String sentTo;
    private String remarks;
    public Transaction(Date transactionDate, Integer transactionAmount,String sentTo, String remarks){
        this.transactionDate=transactionDate;
        this.transactionAmount=transactionAmount;
        this.sentTo=sentTo;
        this.remarks=remarks;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Date getTransactionDate(){
        return transactionDate;
    }
    public Integer getTransactionAmount(){
        return transactionAmount;
    }

    public String getSentTo() {
        return sentTo;
    }

    public String getRemarks() {
        return remarks;
    }
}

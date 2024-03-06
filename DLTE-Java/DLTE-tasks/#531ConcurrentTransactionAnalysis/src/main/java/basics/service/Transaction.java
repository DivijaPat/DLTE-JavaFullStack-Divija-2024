package basics.service;

import java.util.Date;

public class Transaction {
    private Date dateOfTransaction;
    private Double amount;
    private String receiver;
    private String remarks;

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Transaction(Date dateOfTransaction, Double amount, String receiver, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amount = amount;
        this.receiver = receiver;
        this.remarks = remarks;
    }
}

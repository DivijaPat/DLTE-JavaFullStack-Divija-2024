package org.example.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String sender;
    private String receiver;
    private double amount;
    private LocalDateTime transaction_timestamp;

    public Transaction(String sender, String receiver, double amount,LocalDateTime transaction_timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.transaction_timestamp = LocalDateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }


    public LocalDateTime getTransaction_timestamp() {
        return transaction_timestamp;
    }

    public void setTransaction_timestamp(LocalDateTime transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", transaction_timestamp=" + transaction_timestamp +
                '}';
    }
}

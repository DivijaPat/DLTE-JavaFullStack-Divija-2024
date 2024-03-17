package org.example.Entity;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String sender;
    private String receiver;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String sender, String receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

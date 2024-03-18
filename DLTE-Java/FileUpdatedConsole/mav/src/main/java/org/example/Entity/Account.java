package org.example.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String email;
    private Long phoneNumber;
    private double balance;
 //  private List<String> transactionHistory;

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", balance=" + balance +
            //    ", transactionHistory=" + transactionHistory +
                '}';
    }

    public Account(String username, String password, String email, Long phoneNumber, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
      //  this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

  //  public List<String> getTransactionHistory() {
   //     return transactionHistory;
    }

//    public void addToTransactionHistory(String transaction) {
//        transactionHistory.add(transaction);
//    }
}

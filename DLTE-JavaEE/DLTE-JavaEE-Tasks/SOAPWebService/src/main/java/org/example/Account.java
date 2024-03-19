package org.example;


public class Account  {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String email;
    private Long phoneNumber;
    private double balance;


    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", balance=" + balance +
                //      ", transactionHistory=" + transactionHistory +
                '}';
    }

    public Account(String username, String password, String email, Long phoneNumber, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;

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
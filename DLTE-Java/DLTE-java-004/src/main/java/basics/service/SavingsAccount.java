package basics.service;

public class SavingsAccount {
    private String accountHolder;
    private Long accountNumber;
    private double accountBalance;



    public String getAccountHolder(){
        return accountHolder;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}

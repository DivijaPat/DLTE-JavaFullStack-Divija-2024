package basics.service;

public class Account {
    protected Long accountNumber;
    protected Double accountBalance;
    protected String accountHolder;

    public Account(Long accountNumber, Double accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }

}

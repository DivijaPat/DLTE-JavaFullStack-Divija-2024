package basics.service;

import java.io.Serializable;
import java.util.Date;

public class CreditCard implements Serializable {
    private Long cardNumber;
    private Double balance;
    private String cardHolder;
    private Date date;
    private Integer cvv;

    public CreditCard(Long cardNumber, Double balance, String cardHolder, Date date, Integer cvv) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.cardHolder = cardHolder;
        this.date = date;
        this.cvv = cvv;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}

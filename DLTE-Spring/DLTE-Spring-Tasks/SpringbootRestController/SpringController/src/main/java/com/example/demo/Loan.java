package com.example.demo;

import java.util.ArrayList;
import java.util.Date;

public class Loan {
    private Long loanNumber;
    private Integer loanAmount;
    private Date loanDate;
    private String loanStatus;
    private ArrayList<Integer> number;

    public Loan(Long loanNumber, Integer loanAmount, Date loanDate, String loanStatus, ArrayList number) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.number=number;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ",number='"+number+'\''+
                '}';
    }

    public ArrayList getNumber() {
        return number;
    }

    public void setNumber(ArrayList number) {
        this.number = number;
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}
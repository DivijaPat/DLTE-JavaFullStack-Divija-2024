package com.completewebapp.demo;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;


public class TransactionEntity {

    @NotBlank(message = "{field.not.null}")
    private Long transactionId;

    @NotBlank(message = "{field.not.null}")
    @Past(message = "{field.date.error}")
    private Date transactionDate;

    @NotBlank(message = "{field.not.null}")
    private String sentTo;

    @NotBlank(message = "{field.not.null}")
    private String receivedBy;

    @NotBlank(message = "{field.not.null}")
    @Positive(message = "{field.amt.error}")
    private Double amount;

    @Size(max = 255, message = "{field.remark.error}")
    private String remarks;

    public TransactionEntity(Long transactionId, Date transactionDate, String sentTo, String receivedBy, Double amount, String remarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.sentTo = sentTo;
        this.receivedBy = receivedBy;
        this.amount = amount;
        this.remarks = remarks;
    }

    public TransactionEntity() {
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", sentTo='" + sentTo + '\'' +
                ", receivedBy='" + receivedBy + '\'' +
                ", amount=" + amount +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
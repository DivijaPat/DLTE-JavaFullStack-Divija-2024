package first;

import java.util.Date;

public class Transaction {
        private Integer transactionAmount;
        private String transactionTo;
        private String remarks; //Family, Education, Emergency, Bills, Friend

    public Transaction( Integer transactionAmount, String transactionTo, String remarks) {
        this.transactionAmount = transactionAmount;
        this.transactionTo = transactionTo;
        this.remarks = remarks;
    }


    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}


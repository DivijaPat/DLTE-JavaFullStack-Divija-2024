package spring.autowire.dltespringautowire.model;

public class Loan {
    private int loanNumber;
    private String borrowerName;
    private double loanAmount;
    private String loanStatus;
    private String loanType;

    public Loan(int loanNumber, String borrowerName, double loanAmount, String loanStatus, String loanType) {
        this.loanNumber = loanNumber;
        this.borrowerName = borrowerName;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
        this.loanType = loanType;
    }

    public Loan() {
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", borrowerName='" + borrowerName + '\'' +
                ", loanAmount=" + loanAmount +
                ", loanStatus='" + loanStatus + '\'' +
                ", loanType='" + loanType + '\'' +
                '}';
    }
}

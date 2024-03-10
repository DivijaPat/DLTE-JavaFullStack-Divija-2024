package basics.service;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis {
    Transaction [] transaction={
            new Transaction(new Date(2023, 02, 04),6000D, "Raksha", "Family"),
            new Transaction(new Date(2024, 05, 06), 7500D,"Rakshitha", "Friend"),
            new Transaction(new Date(2013, 02, 14), 4300D,"Rashmitha", "Emergengy"),
            new Transaction(new Date(2023, 06, 04), 10000D,"Swasthi", "Medical"),
            new Transaction(new Date(2022, 03, 07), 5500D,"Bhavya", "Family")
    };
    public void run(){
        TransactionAnalysis analysis=new TransactionAnalysis();
        int choice;
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("Enter your choice\n1.Filter based on given ranges of date\n2.Find least amount transferred\n3.Find maximum amount transferred\n4.Find number of transaction made to particular beneficiary\\n5.filter based on particular remark\n");
            choice=scanner.nextInt();
            switch (choice){
                case 1:
                    analysis.filterDate(transaction);
                    break;
                case 2:
                    analysis.minAmount(transaction);
                    break;
                case 3:
                    analysis.maxAmount(transaction);
                    break;
                case 4:
                    analysis.numberOfTransactions(transaction);
                    break;
                case 5:
                    analysis.filterRemarks(transaction);
                    break;
            }
        }

    }

    public void filterDate(Transaction[] transaction){
        String inputDate;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the date(yyyy/mm/dd)");
        inputDate = scanner.next();
        String splitDate[] = inputDate.split("/");
        for (Transaction each : transaction) {
            if ((Integer.parseInt(splitDate[0]) == (each.getDateOfTransaction().getYear())) && (Integer.parseInt(splitDate[1]) == (each.getDateOfTransaction().getMonth())) && (Integer.parseInt(splitDate[2]) == (each.getDateOfTransaction().getDate())))

            {
                System.out.println("Transaction was made to" + each.getTransactionTo());
            }

        }
    }

    public void minAmount(Transaction[] transaction) {
        double minAmount = 0;
        for (Transaction each : transaction) {
            minAmount = each.getAmountInTransaction();
            if (minAmount > each.getAmountInTransaction()){
                minAmount=each.getAmountInTransaction();
            }

        }
        System.out.println("The least amount transferred is" + minAmount);

    }

    public void maxAmount(Transaction[] transaction) {
        double maxAmount =0;
        for (Transaction each : transaction) {
            maxAmount =each.getAmountInTransaction();
            if (maxAmount < each.getAmountInTransaction()){
                maxAmount=each.getAmountInTransaction();
            }

        }
        System.out.println("The max amount transferred is" + maxAmount);
    }

    public void numberOfTransactions(Transaction[] transaction) {
        Scanner scanner = new Scanner(System.in);
        String beneficiary;
        int numberOfTransaction = 0;
        System.out.println("Enter the beneficiary name\n");
        beneficiary = scanner.next();
        for (Transaction each : transaction) {
            if (each.getTransactionTo().equals(beneficiary)) {
                numberOfTransaction++;
            }
        }
        System.out.println("the no of transaction made to" + beneficiary + " is " + numberOfTransaction);
    }

    public void filterRemarks(Transaction[] transaction) {
        Scanner scanner = new Scanner(System.in);
        String remark;
        System.out.println("Enter the remark");
        remark = scanner.next();
        for (Transaction each : transaction) {
            if (each.getRemarks().equals(remark)) {
                System.out.println("The amount transferred to beneficiary is" + each.getAmountInTransaction() + "having remarks" + remark);
            }

        }

    }

    public void displayBeneficiary(){
        for(Transaction each:transaction)
            System.out.println("The Beneficiaries are"+each.getTransactionTo());
    }
    public void displayRemarks(){
        for(Transaction each:transaction)
            System.out.println("The Remarks are"+each.getRemarks());
    }
    public void displayAmount() {
        for(Transaction each:transaction)
            System.out.println("The amount transferred is"+each.getAmountInTransaction());
    }

}



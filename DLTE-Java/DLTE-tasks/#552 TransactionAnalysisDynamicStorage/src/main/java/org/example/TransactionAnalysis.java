package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalysis {
    private List<Transaction> transactionList= new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }
    public List<Transaction> filterByDate(String startDate,String endDate){
        return transactionList.stream().filter(transaction -> transaction.getDateOfTransaction().compareTo(startDate)>=0 && transaction.getDateOfTransaction().compareTo(endDate)<=0).collect(Collectors.toList());
    }
    public Transaction minimumAmountTransaction(){
        return transactionList.stream().min(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
    }
    public Transaction maximumAmountTransaction(){
        return transactionList.stream().max(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
    }
    public List<Transaction> sortTransaction(Comparator<Transaction> comparator){
        return transactionList.stream().sorted(comparator).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        TransactionAnalysis analysis=new TransactionAnalysis();
        analysis.addTransaction(new Transaction("2023-04-13",4000D,"Divija","Family"));
        analysis.addTransaction(new Transaction("2022-12-4",2300D,"Anusha","Education"));
        analysis.addTransaction(new Transaction("2024-01-01",8900D,"Meghana","Emergency"));
        analysis.addTransaction(new Transaction("2020-10-29",10000D,"Arpita","Bills"));
        analysis.addTransaction(new Transaction("2016-11-19",7845D,"Sandeep","Emergency"));
        int option;
        while(true){
            System.out.println("Enter your choice:\n1.Filter based on date of transaction\n2.Find transaction with minimum amount\n3.Find transaction with maximum amount\n4.Sort the transaction\n5.Exit");
            option=scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    System.out.println("Enter start date(yyyy-mm-dd)");
                    String start=scanner.nextLine();
                    System.out.println("Enter end date(yyyy-mm-dd)");
                    String end=scanner.nextLine();
                    List<Transaction> transaction= analysis.filterByDate(start,end);
                    System.out.println("Filtered result is:");
                    System.out.println(transaction+"\n");
                    break;
                case 2:
                    Transaction minimumTransaction=analysis.minimumAmountTransaction();
                    System.out.println("Minimum amount in transaction:"+minimumTransaction);
                    break;
                case 3:
                    Transaction maximumTransaction=analysis.maximumAmountTransaction();
                    System.out.println("Maximum amount in transaction:"+maximumTransaction);
                    break;
                case 4:
                    System.out.println("Enter the attribute on which you wish to sort(date,amount,to,remarks):");
                    String attribute=scanner.next();
                    System.out.println("Enter the order(ascending/descending)");
                    String order=scanner.next();
                    Comparator<Transaction> comparator=null;
                    switch (attribute){
                        case "date": case "Date":comparator=Comparator.comparing(Transaction::getDateOfTransaction);
                        break;
                        case "amount": case "Amount":comparator=Comparator.comparing(Transaction::getAmountInTransaction);
                            break;
                        case "to": case "To":comparator=Comparator.comparing(Transaction::getTransactionTo);
                            break;
                        case "remarks": case "Remarks":comparator=Comparator.comparing(Transaction::getRemarks);
                            break;
                        default:
                            System.out.println("no such attribute");
                    }
                    if(comparator!=null){
                        if(order.equalsIgnoreCase("descending")) {
                            comparator = comparator.reversed();
                            List<Transaction> sortTransaction = analysis.sortTransaction(comparator);
                            System.out.println("Sorted transaction is:");
                            System.out.println(sortTransaction);
                        }
                        else if(order.equalsIgnoreCase("ascending")){
                            List<Transaction> sortTransaction = analysis.sortTransaction(comparator);
                            System.out.println("Sorted transaction is:");
                            System.out.println(sortTransaction);
                        }
                        else
                            System.out.println("Give the order as ascending or descending");
                    }
                    break;
                case 5 :System.exit(0);
                default:
                    System.out.println("invalid choice");
            }
        }
    }
}

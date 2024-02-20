package basics.service;
import java.util.Date;
import java.util.Scanner;
public class TransactionAnalysis {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Transaction myTransaction[] =
                {
                        new Transaction(new Date(2024,04,02),1000,"Sunidhi","Family"),
                        new Transaction(new Date(2024,03,01),1020,"Eeksha","Education"),
                        new Transaction(new Date(2024,04,03),2000,"Sudheesh","Bill"),
                        new Transaction(new Date(2024,04,23),500,"Ankitha","Friend"),
                        new Transaction(new Date(2024,05,02),1400,"Dishitha","Family"),
                        new Transaction(new Date(2024,03,12),900,"Ankitha","Friend"),
                };

        TransactionAnalysis analysis=new TransactionAnalysis();
        while(true){
            System.out.println(" -------Menu------");
            System.out.println("1. Filter based on given date range\n2. least amount transferred\n3. maximum amount transferred\n4.no. of transactions made to particular beneficiary\n5. filter based on particular remarks\n6. sort based on amount in ascending order\n7. sort based on beneficiary in descending order\n8. exit");
            System.out.println("enter your choice:");
            int choice =scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter start date(only the date)");
                    int start=scanner.nextInt();
                    System.out.println("enter end date");
                    int end=scanner.nextInt();
                    analysis.filterBasedOnDate(myTransaction,start,end);
                    break;
                case 2:
                    analysis.minimumAmount(myTransaction);
                    break;
                case 3:
                    analysis.maximumAmount(myTransaction);
                case 4:
                    System.out.println("enter beneficiary as single name");
                    String name=scanner.next();
                    analysis.totalTransaction(myTransaction,name);
                    break;
                case 5:
                    System.out.println("enter remarks(Family,Friend,Education,Emergency,Bills)");
                    String remarks=scanner.next();
                    for(Transaction each:myTransaction){
                        if(each.getRemarks().equals(remarks)){
                            System.out.println(each.getSentTo()+" "+each.getTransactionAmount()+" "+each.getTransactionDate());
                        }
                    }
                    break;
                case 6:
                    analysis.sortedAmount(myTransaction);
                    break;
                case 7:
                     analysis.sortedBeneficiary(myTransaction);
                default:
                    System.exit(0);
            }
        }
    }
    private void sortedAmount(Transaction[] myTransaction) {
        System.out.println("before sorting:");
        for (Transaction each : myTransaction) {
            System.out.println(each.getTransactionAmount());
        }

        for (int index = 0; index < myTransaction.length; index++) {
            for (int next = 0; next < myTransaction.length - index - 1; next++) {
                if (myTransaction[next].getTransactionAmount().compareTo(myTransaction[next + 1].getTransactionAmount()) < 0) {
                    Transaction backup = myTransaction[next];
                    myTransaction[next] = myTransaction[next + 1];
                    myTransaction[next + 1] = backup;
                }
            }
        }
        System.out.println("After sorting");
        for (Transaction each : myTransaction) {
            System.out.println(each.getTransactionAmount());
        }
    }
    private void sortedBeneficiary(Transaction[] myTransaction){
        System.out.println("Before sorting:");
        for(Transaction each:myTransaction){
            System.out.println(each.getSentTo());
        }

        for(int index=0;index<myTransaction.length;index++){
            for(int next=0;next<myTransaction.length-index-1;next++){
                if(myTransaction[next].getSentTo().compareTo(myTransaction[next+1].getSentTo())<0){
                    Transaction backup= myTransaction[next];
                    myTransaction[next]=myTransaction[next+1];
                    myTransaction[next+1]=backup;
                }
            }
        }
        System.out.println("After sorting");
        for(Transaction each:myTransaction){
            System.out.println(each.getSentTo());
        }
    }




    private void totalTransaction(Transaction[] myTransaction,String name){
        int countTransaction=0;
        for(Transaction each:myTransaction){
            if(each.getSentTo().equals(name)){
                countTransaction++;
            }

        }
        System.out.println("no. of transactions made by "+name+" is "+countTransaction);
    }
    private void minimumAmount(Transaction[] myTransaction) {
        int amount = myTransaction[0].getTransactionAmount();
        for (Transaction each : myTransaction) {
            if (amount > each.getTransactionAmount())
                amount = each.getTransactionAmount();
        }
        System.out.println("name      minimum amount");
        for (Transaction each : myTransaction) {
            if(each.getTransactionAmount()==amount)
                System.out.println(each.getSentTo()+" "+amount);
        }
    }
    private void maximumAmount(Transaction[] myTransaction){
        int amount=myTransaction[0].getTransactionAmount();
        for(Transaction each:myTransaction){
            if(amount<each.getTransactionAmount())
                amount=each.getTransactionAmount();
        }
        System.out.println("name      maximum amount");
        for (Transaction each : myTransaction) {
            if(each.getTransactionAmount()==amount)
                System.out.println(each.getSentTo()+" "+amount);
        }

    }
    private void filterBasedOnDate(Transaction[] myTransaction,int start,int end){
        System.out.println("transaction between dates "+start+" and "+end+" is:");
        for(Transaction each:myTransaction){
            if(each.getTransactionDate().getDate() >= start && each.getTransactionDate().getDate()<= end)
                System.out.println(each.getSentTo()+" "+each.getTransactionAmount()+" "+each.getTransactionDate().getDate());
        }
    }
}

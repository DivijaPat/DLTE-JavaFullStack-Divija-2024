package basics.service;
import java.util.Scanner;
public class Transaction {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        double amountInAccount=3000, afterTransaction=0.0;
        int countDebit=0;
        for(int index=0;index<10;index++)
        {
            System.out.println("Enter the amount after transaction");
            afterTransaction=scanner.nextDouble();
            if(amountInAccount>afterTransaction)
                countDebit++;
            amountInAccount=afterTransaction;
        }
        System.out.println("Total count of debits:"+countDebit);
    }
}

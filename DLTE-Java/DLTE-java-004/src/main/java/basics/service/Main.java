package basics.service;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GPay gPay=new GPay(783456578765L,65700D,"Divija",783456578765L,7765,8448,"Divija");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        int choice=0;
        Double amount;
        System.out.println("Enter your choice:\n1. Withdraw amount\n2.Pay the Bill");
        choice=scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("enter the amount to be withdrawn");
                amount=scanner.nextDouble();
                gPay.withdraw(amount);
                break;
            case 2:
                System.out.println("enter the biller first name");
                String billerName = scanner.next();
                System.out.println("enter the billed amount");
                Double billedAmount=scanner.nextDouble();
                System.out.println("enter the bill type");
                String billType= scanner1.nextLine();
                gPay.payBill(billerName,billedAmount,billType);
                break;

            default:
                System.exit(0);


        }
        scanner.close();

    }
}

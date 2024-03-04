package basics.service;
import java.util.Scanner;
public class CLI_FD_MaturityAmount {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int choice=0;







        int tenureFD;
        double  principalAmount, rateOfInterest, matureFD, interest;
        System.out.println("------FD Maturity Amount Calculator------");
        System.out.println("Enter principal amount:");
        principalAmount= scanner.nextDouble();
        System.out.println("Enter rate of interest:");
        rateOfInterest = scanner.nextDouble();
        interest = rateOfInterest/100;
        System.out.println("Enter tenure:");
        tenureFD = scanner.nextInt();
        matureFD = principalAmount *Math.pow((1+interest),tenureFD);
        System.out.println("Mature amount is "+matureFD+" for principle amount "+principalAmount+" with rate of interest of "+rateOfInterest+" and tenure of "+tenureFD+" years");

        //Personal Loan
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="";
        Long mobileNumber=0L,aadhaar=0L;
        System.out.println("-------------Welcome to MyBank--------------");

        System.out.println("Enter the name:");
        borrowerName=scanner.nextLine();
        System.out.println("Enter the aadhaar number:");
        aadhaar=scanner.nextLong();
        System.out.println("Enter the pan number:");
        borrowerPan=scanner.next();
        System.out.println("Let us know your income type(salaried/self-employed):");
        borrowerIncomeType=scanner.nextLine();
        System.out.println("Enter the mobile number:");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the email address:");
        borrowerAddress=scanner.next();


        System.out.println("Dear "+borrowerName+" thanks for showing the interest on taking personal loan and further detailes will be shared to you");


        //internet banking
        System.out.println("Enter data to avail mobile banking");
        String check="";
        System.out.println("Enter account holder name");
        String accName=scanner.next();
        System.out.println("Enter mobile number");
        mobileNumber =scanner.nextLong();
        System.out.println("enter email address");
        String emailAd=scanner.next();
        System.out.println("enter debit card number");
        Long card=scanner.nextLong();
        System.out.println("Internet banking facility will be availed soon");

        //mobile banking
        System.out.println("Enter data to avail mobile banking");
        System.out.println("Enter account holder name");
        String accountName=scanner.next();
        System.out.println("Enter mobile number");
        mobileNumber =scanner.nextLong();
        System.out.println("enter email address");
        String emailAdd=scanner.next();
        System.out.println("enter debit card number");
        Long dcard=scanner.nextLong();
        System.out.println("Is internet banking available in your account(yes/no)");
        check= scanner.next();
        System.out.println("your upi id is"+mobileNumber+"@bank");
        System.out.println("thank you"+accountName+"your mobile banking facility will be availed soon");


    }
}

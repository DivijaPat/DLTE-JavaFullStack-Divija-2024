package basics.service;
import java.util.Scanner;
//Command line interaction:car loan
/*
personal details:name,aadhaar,pan,address,mobile,email
Income:salaries,self-employed:TIR
 */
public class Blocks {
    public static void main(String args[]){
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="";
        Long mobileNumber=0L,aadhaar=0L;
        System.out.println("-------------Welcome to MyBank--------------");
        Scanner scanner=new Scanner(System.in);
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


        System.out.println("Dear "+borrowerName+" thanks for showing the interest on taking care of and further detailes will be delivered to you using" +borrowerAddress+"or SMS to"+mobileNumber);
    }
}

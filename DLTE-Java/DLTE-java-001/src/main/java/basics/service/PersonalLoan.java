package basics.service;

import java.util.Scanner;

public class PersonalLoan {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
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
        borrowerIncomeType=scanner.nextLine();
        System.out.println("Enter the mobile number:");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the email address:");
        borrowerAddress=scanner.next();
        System.out.println("Dear "+borrowerName+"( "+mobileNumber+" )thanks for showing the interest on taking personal loan and further detailes will be shared to you");


    }
}

package basics.service;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexValidate {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String borrowerName="", borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncomeType="",borrowerAadhaar="", borrowerMobile="";
        System.out.println("------welcome to Bank------");
        System.out.println("Enter your name:");
        borrowerName= scanner.nextLine();
        String nameExpression="^[a-zA-Z .']+$";
        Pattern pattern = Pattern.compile(nameExpression);
        Matcher matcher=pattern.matcher(borrowerName);
        if(matcher.matches())
            System.out.println("valid name");
        else
            System.out.println("Invalid name");
        System.out.println("Enter Aadhaar:");
        borrowerAadhaar= scanner.next();
        String aadharExpression ="[1-9]{1}[0-9]{11}";
        Pattern patternAdhar=Pattern.compile(aadharExpression);
        Matcher matcherAadhar=patternAdhar.matcher(borrowerAadhaar);
        if(matcherAadhar.matches())
            System.out.println("valid Aadhar number");
        else
            System.out.println("Invalid aadhar");
        System.out.println("Enter Pan number:");
        borrowerPan= scanner.next();
        String panExpression ="[A-Z]{5}[0-9]{4}[A-Z]";
        Pattern patternPan=Pattern.compile(panExpression);
        Matcher matcherPan=patternPan.matcher(borrowerPan);
        if(matcherPan.matches())
            System.out.println("valid PAN number");
        else
            System.out.println("Invalid Pan number");

        System.out.println("Fill in to know income type:");
        borrowerIncomeType= scanner.next();
        System.out.println("Enter mobile number:");
        borrowerMobile=scanner.next();
        String mobileexpression= "[6-9]{1}[0-9]{9}";
        Pattern patternPhone= Pattern.compile(mobileexpression);
        Matcher matcherPhone = patternPhone.matcher(borrowerMobile);
        if(matcherPhone.matches())
            System.out.println("Valid phone number");
        else
            System.out.println("Invalid phone number");

        System.out.println("Enter email id:");
        borrowerEmail =scanner.next();
        int _atPos = borrowerEmail.indexOf('@');
        int _dotPos = borrowerEmail.indexOf('.');
        if(_dotPos-_atPos>=3)
            System.out.println("valid Email");
        else
            System.out.println("Invalid Email");

    }
}

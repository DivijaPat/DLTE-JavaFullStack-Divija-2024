package basics.service;
import java.util.Scanner;
public class CLI_FD_MaturityAmount {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
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
    }
}

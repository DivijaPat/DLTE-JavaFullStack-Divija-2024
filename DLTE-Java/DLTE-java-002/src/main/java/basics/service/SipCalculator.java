package basics.service;
import java.util.Scanner;
public class SipCalculator {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        double maturityAmount, principle, compoundInterest, rateOfInterest ;
        int noOfPayments;
        System.out.println("Enter the principle");
        principle = scanner.nextDouble();
        System.out.println("Enter the rate of interest");
        rateOfInterest = scanner.nextDouble();
        System.out.println("Enter the no. of payments you have made");
        noOfPayments = scanner.nextInt();
        compoundInterest = rateOfInterest/(12*100);
        double multiplier = Math.pow(compoundInterest+1,noOfPayments);
        maturityAmount= principle*((multiplier-1)/compoundInterest)*(1+compoundInterest);
        System.out.println(maturityAmount+" Rs per year");
    }
}

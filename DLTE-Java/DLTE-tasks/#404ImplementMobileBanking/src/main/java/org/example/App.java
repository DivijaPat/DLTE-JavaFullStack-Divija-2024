package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main (String[] args) {
        gPay gPayAccount = new gPay(654321, 150000, "anupama", "123-456-789", "1222");
        double withdrawAmount = 15000.0;
        System.out.println("Amount to be withdrawn: "+withdrawAmount);
        if (gPayAccount.approveWithdraw(withdrawAmount)) {
            System.out.println("Withdraw approved, your balance is " + (gPayAccount.getAccountBalance() - withdrawAmount));
        } else
            System.out.println("Insufficient balance, withdraw denied.");
        String billerName = "XYZ";
        double billedAmount = 800;
        String billerType = "Current Bill";
        String upiPin = "1222";
        gPayAccount.payBill(billerName, billedAmount, billerType, upiPin);
    }
}

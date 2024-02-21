package basics.service;
import java.util.Scanner;
public class GPay extends DebitCard {
    Scanner scanner=new Scanner(System.in);
    private Integer upiPin;
    private String userName;

    public GPay(Long accountNumber, Double accountBalance, String accountHolder, Long cardNumber, Integer cardPin, Integer upiPin, String userName) {
        super(accountNumber, accountBalance, accountHolder, cardNumber, cardPin);
        this.upiPin = upiPin;
        this.userName = userName;
    }

    public void payBill(String billerName,Double billedAmount,String billType) {
        System.out.println("enter the UPI pin");
        int pin = scanner.nextInt();
        if (pin==upiPin) {
            accountBalance-=billedAmount;
            System.out.println(billerName + " your " + billType + " of Rs " + billedAmount + " has been paid Remaining amount is "+accountBalance);
        }
        else{
            System.out.println("invalid pin!!");
        }

    }
}

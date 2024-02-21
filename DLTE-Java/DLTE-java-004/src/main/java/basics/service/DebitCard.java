package basics.service;
import java.util.Scanner;
public class DebitCard extends Account{
    private Long cardNumber;
    private Integer cardPin;

    public DebitCard(Long accountNumber, Double accountBalance, String accountHolder, Long cardNumber, Integer cardPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }


    public void withdraw(Double amount){
        Scanner scanner=new Scanner(System.in);
        if(amount< accountBalance){
            System.out.println("enter your card PIN");
            int pin=scanner.nextInt();
            if(pin==cardPin) {
                accountBalance -= amount;
                System.out.println("The balance in the account is:"+accountBalance);
            }
            else{
                System.out.println("Wrong pin!!");
            }
        }
        else
            System.out.println("insufficient balance!!");

        scanner.close();
    }



}

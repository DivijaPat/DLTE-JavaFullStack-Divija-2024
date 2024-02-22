package basics.service;

import java.util.Date;
import java.util.Scanner;
public class CreditCardMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filterDate = "";
        int option, creditCardPin, incorrectLogin = 1;
        Integer start, end;
        Long creditCardNumber;
        CreditCard[] myBank = {
                new CreditCard(9735678765675L, "Eeksha", new Date(2034, 12, 30), 666, 70000, new Date(2024, 2, 11), new Date(2024, 3, 30), 1111),
                new CreditCard(9343204345442L, "Ankitha", new Date(2029, 1, 5), 741, 50000, new Date(2024, 3, 5), new Date(2024, 3, 22), 9999),
                new CreditCard(7865178764541L, "Akshira", new Date(2031, 5, 15), 327, 100000, new Date(2024, 4, 10), new Date(2024, 6, 11), 9864),
                new CreditCard(1234565756769L, "Anusha", new Date(2028, 8, 11), 783, 100000, new Date(2024, 5, 18), new Date(2024, 8, 29), 1945),
        };

        CreditCardMethods support = new CreditCardMethods();
        System.out.println("Enter your choice:\n 1.Filter based on limit amount\n2. filter based on date of bill payment\n3.Update the PIN\n4. update amount limit of those whose bill generation date is on 5");
        option = scanner.nextInt();
        switch (option) {
            case 1: try{
                System.out.println("enter start card limit:");
                start = scanner.nextInt();
                System.out.println("enter end card limit:");
                end = scanner.nextInt();
                support.filterBasedOnLimit(myBank, start, end);
                break;}
                catch( MyBankCreditCardException  myBankCreditCardException){

                }
            case 2:
                System.out.println("enter date(dd-mm-yyyy)");
                filterDate = scanner.next();
                support.filterBillPayment(myBank,filterDate);
                break;

        }
        scanner.close();
    }

    public void filterBasedOnLimit(CreditCard[] myBank,Integer start,Integer end){
        for(CreditCard each:myBank){
            if(each.getCreditCardLimit()>=start && each.getCreditCardLimit()<=end){
                System.out.println(each.getCreditCardHolder()+" your credit card limit is between "+start+" and "+ end );
            }
            else
                throw new  MyBankCreditCardException();

        }

    }

    public void filterBillPayment(CreditCard[] myBank,String date) {
        String splitDate[] = date.split("-");
        for (CreditCard each:myBank) {
            if (Integer.parseInt(splitDate[0]) == each.getDateOfBillPayment().getDate() && Integer.parseInt(splitDate[1]) == each.getDateOfBillPayment().getMonth() && Integer.parseInt(splitDate[2]) == each.getDateOfBillPayment().getYear()) {
                System.out.println(each.getCreditCardHolder() + " " + each.getCreditCardNumber());
            }
        }
    }

    public void updatePin(CreditCard[] myBank,long creditCardNumber){
        Scanner scanner=new Scanner(System.in);
        int newcreditCardPin;
        System.out.println("enter new pin:");
        newcreditCardPin=scanner.nextInt();
        for(CreditCard each:myBank){
           if(each.getCreditCardNumber().equals(creditCardNumber)){
               each.setCreditCardPin(newcreditCardPin);
                System.out.println("Your new credit card pin is:"+newcreditCardPin);
            }

        }
        scanner.close();
    }



}

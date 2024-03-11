package basics.service;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class CreditCardMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       int startDate,endDate;
        int option, creditCardPin, incorrectLogin = 1;
        Integer start, end;
        Long creditCardNumber;
        CreditCard[] myBank = {
                new CreditCard(9735678765675L, "Eeksha", new Date(2034, 12, 30), 666, 70000, new Date(2024, 2, 11), new Date(2024, 3, 30), 1111),
                new CreditCard(9343204345442L, "Ankitha", new Date(2029, 1, 5), 741, 50000, new Date(2024, 3, 5), new Date(2024, 3, 22), 9999),
                new CreditCard(7865178764541L, "Akshira", new Date(2031, 5, 15), 327, 100000, new Date(2024, 4, 10), new Date(2024, 6, 11), 9864),
                new CreditCard(1234565756769L, "Anusha", new Date(2028, 8, 11), 783, 100000, new Date(2024, 5, 18), new Date(2024, 8, 29), 1945),
        };
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        //Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        Logger logger= LoggerFactory.getLogger(CreditCardMethods.class);
        CreditCardMethods support = new CreditCardMethods();
        System.out.println(resourceBundle.getString("menu.title"));
        System.out.println(resourceBundle.getString("menu.choices"));
        option = scanner.nextInt();
        switch (option) {
            case 1: try{
                System.out.println(resourceBundle.getString("start.limit"));
                start = scanner.nextInt();
                System.out.println(resourceBundle.getString("end.limit"));
                end = scanner.nextInt();
                support.filterBasedOnLimit(myBank, start, end);
                }
                catch( MyBankCreditCardException  myBankCreditCardException){
                    System.out.println("None");
                   // logger.log(Level.WARNING,myBankCreditCardException.toString());
                    logger.warn(myBankCreditCardException.toString());
                }
                break;
            case 2:
                try {
                    System.out.println(resourceBundle.getString("start.date"));
                    startDate = scanner.nextInt();
                    System.out.println(resourceBundle.getString("end.date"));
                    endDate = scanner.nextInt();
                    support.filterBillPayment(myBank,startDate,endDate);
                } catch(MyBankCreditCardException myBankCreditCardException){
                    System.out.println("No customers found");
                   // logger.log(Level.WARNING,myBankCreditCardException.toString());
                    logger.warn(myBankCreditCardException.toString());
                }
                break;
            default:
                System.out.println("Thank You");
                scanner.close();
                System.exit(0);
        }

    }

    public void filterBasedOnLimit(CreditCard[] myBank,Integer start,Integer end){
        int flag=0;
        for(CreditCard each:myBank) {
            if (each.getCreditCardLimit() >= start && each.getCreditCardLimit() <= end) {
                flag = 1;
                System.out.println(each.getCreditCardHolder() + " your credit card limit is between " + start + " and " + end);
            }
        }
        if(flag==0){
            throw new MyBankCreditCardException();

        }

    }

    public void filterBillPayment(CreditCard[] myBank,int startDate,int endDate) {
        int flag=0;
        System.out.println("customers who made bill payment between "+startDate+" and"+endDate);
        for (CreditCard each:myBank) {
            if (each.getDateOfBillPayment().getDate() >= startDate &&  each.getDateOfBillPayment().getDate()<=endDate ){
                flag=1;
                System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillPayment().getDate());
            }
        }
        if(flag==0)
             throw new MyBankCreditCardException();
    }
}

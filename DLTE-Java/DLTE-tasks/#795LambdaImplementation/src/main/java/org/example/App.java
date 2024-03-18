package org.example;

import java.util.Date;

import static org.example.MyBank.loanList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //hardcoding list values
        Loan loan1 = new Loan(1, 5000.0, new Date("6/7/2024"), "Open", "Divija", 6363276256L);
        Loan loan2 = new Loan(2, 8000.0, new Date("6/9/2024"), "Closed", "Dishitha", 9876543210L);
        Loan loan3 = new Loan(3, 10000.0, new Date("6/4/2024"), "Open", "Sunidhi", 7890654321L);

        // adding loans to the loanList
        loanList.add(loan1);
        loanList.add(loan2);
        loanList.add(loan3);
        Date StartDate = new Date("6/4/2024");
        Date EndDate = new Date("6/9/2024");
        //using lambda
        MyBank myBank = ((startDate, endDate)-> {
            for (Loan loan : loanList) {
                if (loan.getLoanDate().before(endDate) && loan.getLoanDate().after(startDate)) {
                    System.out.println(loan);
                }
            }
        });


        myBank.filterByRange(StartDate, EndDate);
    }
}

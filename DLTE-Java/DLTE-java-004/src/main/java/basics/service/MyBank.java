package basics.service;

import java.util.Date;

public interface MyBank {
   Loan loan[]=new Loan[10];
   void addNewLoan(int size);
   void checkAvailableLoans();
   void checkClosedLoans();

}

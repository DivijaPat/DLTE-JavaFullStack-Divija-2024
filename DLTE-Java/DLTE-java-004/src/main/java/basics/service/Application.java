package basics.service;
import java.util.Scanner;
public class Application implements MyBank {
 int loanSize=0;
 Scanner scanner= new Scanner(System.in);
 Scanner scanner1=new Scanner(System.in);
 Loan[] loan= new Loan[10];

    public void addNewLoan(int size){
        for(int index=0;index<size;index++){
            System.out.println("Enter the inputs for "+(index+1)+" user:");
            System.out.println("Enter loan number:");
            Long loanNumber=scanner.nextLong();
            System.out.println("Enter loan amount:");
            Double loanAmount=scanner.nextDouble();
            System.out.println("Enter loan date:");
            String loanDate=scanner.next();
            System.out.println("Enter loan status(open/closed):");
            String  loanStatus=scanner.next();
            System.out.println("Enter borrower name:");
            String borrowerName=scanner1.nextLine();
            System.out.println("Enter borrower contact number:");
            Long borrowerContact=scanner.nextLong();
            loan [index]=new Loan(loanNumber,loanAmount,loanDate,loanStatus,borrowerName,borrowerContact);
        }

    }
    public void checkAvailableLoans(){
        for(int index=0;index<loan.length;index++){
            if(loan[index]!=null) {
                if (loan[index].getLoanStatus().equalsIgnoreCase("open"))
                    System.out.println(loan[index].toString());
            }
        }
    }

    public void checkClosedLoans(){
        for(int index=0;index<loan.length;index++){
            if(loan[index]!=null) {
                if (loan[index].getLoanStatus().equalsIgnoreCase("closed"))
                    System.out.println(loan[index].toString());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        Application application = new Application();
        while (true) {
        System.out.println("Enter your choice:\n1. Add new loan\n2. Check available loans\n3. Check closed loans\n");
        choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter no. of loans:");
                    int loanSize = scanner.nextInt();
                    application.addNewLoan(loanSize);
                    System.out.println("Loan added successfully");
                    break;
                case 2:
                    application.checkAvailableLoans();
                    break;
                case 3:
                    application.checkClosedLoans();
                    break;
                default:

                    System.exit(0);
            }

        }

    }


}

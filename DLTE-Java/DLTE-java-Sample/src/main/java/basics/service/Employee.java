package basics.service;

import basics.service.CollectDetails;
import basics.service.EmployeeAddressDetails;
import basics.service.EmployeeDetails;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;

public class Employee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CollectDetails collectDetails = new CollectDetails();
        DisplayDetails details=new DisplayDetails();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
       //Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        ArrayList<EmployeeDetails> employeeDetails = new ArrayList<>();
        ArrayList<EmployeeAddressDetails> employeeAddressDetails = new ArrayList<>();
        String option="";
        System.out.println();
        while (true){
            System.out.println(resourceBundle.getString("menu.choice"));
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    do {
                        System.out.println(resourceBundle.getString("enter.details"));
                        employeeDetails.add(collectDetails.collect());
                        employeeAddressDetails.add(collectDetails.collectAddress());
                        System.out.println(resourceBundle.getString("ask.more"));
                        option=scanner.next();

                    }while (option.equalsIgnoreCase("yes"));
                    break;
                case 2:
                    details.displayDetails(employeeDetails,employeeAddressDetails);
                    break;
                default:System.exit(0);
            }

        }


    }
}

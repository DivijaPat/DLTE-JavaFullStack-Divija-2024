package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )

   {
        Scanner scanner=new Scanner(System.in);
        EmployeeDetails employeeDetails=new FileRepositoryImplementation();
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Logger logger= LoggerFactory.getLogger(App.class);
        EmployeeAddress employeeAddress;
        Employee employee;

        while (true){
            System.out.println(resourceBundle.getString("menu.choose"));
            switch (scanner.nextInt()){
                case 1:
                    do{
                        System.out.println(resourceBundle.getString("enter.name"));
                        String employeeName = scanner.next();
                        scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.id"));
                        String employeeId=scanner.next();
                        scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.emailId"));
                        String emailId = scanner.next();
                        System.out.println(resourceBundle.getString("enter.phone"));
                        long phoneNumber = scanner.nextLong();
                        System.out.println(resourceBundle.getString("enter.permanentAddress"));
                        System.out.println(resourceBundle.getString("enter.street"));
                        String permanentStreet = scanner.nextLine();
                        scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.HouseName"));
                        String permanentHouseName = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.city"));
                        String permanentCity = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.state"));
                        String permanentState = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.pincode"));
                        int permanentPinCode = scanner.nextInt();
                        System.out.println(resourceBundle.getString("enter.temporaryaddress"));
                        System.out.println(resourceBundle.getString("enter.street"));
                        scanner.nextLine();
                        String temporaryStreet = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.HouseName"));
                        String temporaryHouseName = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.city"));
                        String temporaryCity = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.state"));
                        String temporaryState = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.pincode"));
                        int temporaryPinCode = scanner.nextInt();
                        employeeAddress = new EmployeeAddress(permanentStreet, permanentHouseName, permanentCity, permanentState, permanentPinCode, temporaryStreet, temporaryHouseName, temporaryCity, temporaryState, temporaryPinCode);
                        employee = new Employee(employeeName,employeeId,emailId,phoneNumber, employeeAddress);
                        //System.out.println(resourceBundle.getString("add.success"));
                        logger.info(resourceBundle.getString("add.success"));
                        List<Employee> employeeList1=new ArrayList<>();
                        employeeList1.add(employee);
                        employeeDetails.create(employeeList1);
                        System.out.println(resourceBundle.getString("add.more"));
                    }while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                    break;
                case 2:
                    System.out.println(employeeDetails.read());
                    break;
                case 3:
                    System.out.println(resourceBundle.getString("enter.pincode"));
                    System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                    break;
                default:
                    System.out.println("Invalid choice");
                    System.exit(0);
            }
        }
    }
}

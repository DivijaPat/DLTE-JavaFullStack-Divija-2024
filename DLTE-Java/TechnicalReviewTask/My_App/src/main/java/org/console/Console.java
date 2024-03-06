package org.console;
import org.filestorage.Employee;
import org.filestorage.EmployeeDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Console {

    private static Console employeeRepository=new Console();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
         List<Employee> employees = new ArrayList<>();
        EmployeeDetails employeeDetails=new EmployeeDetails();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter your choice: \n1.Add Employee\n2.Display Employee\n3.Logout");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    do{
                        inputDetails(scanner);
                    }while(scanner.nextLine().equalsIgnoreCase("yes"));
                    break;
                case 2:
                 employeeDetails.read();
                    break;
                case 3:exit(0);
                default:
                    System.out.println("invalid choice");
                    System.exit(0);
            }
        }
    }
    private static void inputDetails(Scanner scanner){
        // Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your name :");
        //Scanner scanner=new Scanner(System.in);
        String employeeName = scanner.nextLine();
//            System.out.println("Enter your middle name :");
//            String employeeMiddleName = scanner.nextLine();
//            System.out.println("Enter your Last name :");
//            String employeeLastName = scanner.nextLine();

        System.out.println("Enter your permanent address :");
        System.out.println("Enter the Address :");
        String permanentAddress = scanner.nextLine();
        System.out.println("Enter the House Name" + " :");
        String permanentHouseName = scanner.nextLine();
        System.out.println("Enter the city :");
        String permanentCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String permanentState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int permanentPinCode = scanner.nextInt();
        System.out.println("Enter your temporary address :");
        System.out.println("Enter the Address :");
        scanner.nextLine();
        String temporaryAddress = scanner.nextLine();
        System.out.println("Enter the House Number :");
        String temporaryHouseName = scanner.nextLine();
        System.out.println("Enter the city :");
        String temporaryCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String temporaryState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int temporaryPinCode = scanner.nextInt();
        // employeeAddress = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);

        System.out.println("Enter the Email Id :");
        String emailId = scanner.next();
        System.out.println("Enter the Phone Number :");
        long phoneNumber = scanner.nextLong();
        //employeeInformation = new EmployeeInformation(emailId, phoneNumber);
        //employee = new Employee(employeeName, employeeAddress, employeeInformation);
        System.out.println("Employee added successfuly");
        //Employee employee
        //employeeRepository.createEmployee(employeeName,permanentAddress,permanentHouseNumber,permanentCity,permanentState,permanentPinCode,temporaryAddress,temporaryHouseNumber,temporaryCity,temporaryState,temporaryPinCode,emailId,phoneNumber);

    }
}

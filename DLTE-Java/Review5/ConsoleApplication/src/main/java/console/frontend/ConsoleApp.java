package console.frontend;

import console.pojo.Employee1;
import console.pojo.EmployeeAddress1;
import console.pojo.EmployeeBasicDetails1;
import exception.EmployeeNotFoundException;
import org.example.DatabaseRepositoryImplementation;
import org.example.Details.Employee;
import org.example.Details.EmployeeAddress;
import org.example.Details.EmployeeBasicDetails;
import org.example.Details.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConsoleApp {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            //InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
            InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
            Logger logger= LoggerFactory.getLogger(ConsoleApp.class);
            Validation validation=new Validation();
            try {
                System.out.println(resourceBundle.getString("greeting.user"));
                List<Employee> employees = new ArrayList<>();
                while (true) {
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    switch (scanner.nextInt()) {
                        case 1:do {
                            System.out.println(resourceBundle.getString("enter.employeeDetails"));
                            System.out.print(resourceBundle.getString("Enter.name"));
                            scanner.nextLine();
                            String name = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.id"));
                            String id = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.emailId"));
                            String email = scanner.nextLine();

                            if (!validation.isValidEmail(email)) {
                                System.out.println(resourceBundle.getString("invalid.email"));
                                break;
                            }

                            System.out.print(resourceBundle.getString("enter.phone"));
                            long phoneNumber = Long.parseLong(scanner.nextLine());

                            if (!validation.isValidPhoneNumber(phoneNumber)) {
                                System.out.println(resourceBundle.getString("invalid.Phone"));
                                break;
                            }

                            System.out.println(resourceBundle.getString("enter.permanentAddress"));
                            System.out.print(resourceBundle.getString("enter.street"));
                            String permanentAddress = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.HouseName"));
                            String permanentHouseName = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.city"));
                            String permanentCity = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.state"));
                            String permanentState = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.pincode"));
                            int permanentPinCode = Integer.parseInt(scanner.nextLine());

                            if (!validation.isValidPin(permanentPinCode)) {
                                System.out.println(resourceBundle.getString("invalid.Pin"));
                                break;
                            }

                            System.out.println(resourceBundle.getString("enter.temporaryaddress"));
                            System.out.print(resourceBundle.getString("enter.address"));
                            String temporaryAddress = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.HouseName"));
                            String temporaryHouseName = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.city"));
                            String temporaryCity = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.state"));
                            String temporaryState = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.pincode"));
                            int temporaryPinCode = Integer.parseInt(scanner.nextLine());

                            if (!validation.isValidPin(temporaryPinCode)) {
                                System.out.println(resourceBundle.getString("invalid.Pin"));
                                break;
                            }

                            EmployeeBasicDetails1 basicDetails = new EmployeeBasicDetails1(name, id, email, phoneNumber);
                            EmployeeAddress1 permanentAddress1 = new EmployeeAddress1(permanentAddress, permanentHouseName, permanentState, permanentCity, permanentPinCode);
                            EmployeeAddress1 temporaryAddress1 = new EmployeeAddress1(temporaryAddress, temporaryHouseName, temporaryState, temporaryCity, temporaryPinCode);

                            Employee1 employee = new Employee(basicDetails, permanentAddress1, temporaryAddress1);
                            List<Employee> employeeInfo = new ArrayList<>();
                            employeeInfo.add(employee);
                            inputEmployeeDetails.create(employeeInfo);

                            System.out.print(resourceBundle.getString("add.more"));
                        }while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                            break;
                        case 2:
                            System.out.println(resourceBundle.getString("enter.id"));
                            String employeeId = scanner.next();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
                                System.out.println(employee.displayEmployeeDetails());
                            } catch (EmployeeNotFoundException e) {
                                System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                               // logger.warn(resourceBundle.getString("employee.not.found"));
                            }
                            break;

                        case 3:
                            List<Employee> employeesList = inputEmployeeDetails.read();
                            if (!employeesList.isEmpty()) {
                                for (Employee emp : employeesList) {
                                    System.out.println(emp.displayEmployeeDetails());
                                    System.out.println();
                                }
                            } else {
                               // System.out.println(resourceBundle.getString("employee.not.found"));
                                logger.warn(resourceBundle.getString("employee.not.found"));
                            }
                            break;
                        case 4:
                            System.out.println(resourceBundle.getString("enter.pincode"));
                            int pinCode = scanner.nextInt();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
                                System.out.println(employee.displayEmployeeDetails());
                            } catch (EmployeeNotFoundException e) {
                                //System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                            }
                            break;
                        case 5:
                            System.exit(0);
                    }
                }
            } finally {

                inputEmployeeDetails.closeConnections();
                scanner.close();
            }
        }
    }


}
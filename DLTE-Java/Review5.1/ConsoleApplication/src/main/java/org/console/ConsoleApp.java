package org.console;
import org.dbRepository.DatabaseRepositoryImplementation;
import org.dbRepository.InputEmployeeDetails;
import org.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ConsoleApp {


    public static void main( String[] args )
    {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        Scanner scanner3=new Scanner(System.in);
        InputDetailsCollectAndDisplay inputDetailsCollectAndDisplay=new InputDetailsCollectAndDisplay();
        try {
            employeeDetails = new ReadAndDisplayUsingDatabase();
            while (true) {
                System.out.println(resourceBundle.getString("menu.display"));
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        inputDetailsCollectAndDisplay.callCollectDetails(employeeDetails);
                        break;
                    case 2:
                        inputDetailsCollectAndDisplay.callDisplayRequired(employeeDetails);
                        break;
                    case 3:
                        inputDetailsCollectAndDisplay.callDisplayAll(employeeDetails);
                        break;
                    case 4:
                        inputDetailsCollectAndDisplay.callDisplayRequiredPincode(employeeDetails);
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println(resourceBundle.getString("invalid.choice"));
                        continue;
                }
            }
        }catch(EmployeeExceptions employeeExceptions){
            System.out.println(employeeExceptions.getErrorMessage());
        } catch (InputMismatchException e){
            System.out.println(resourceBundle.getString("number.only"));
            scanner.next();
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        finally {
            scanner.close();
            scanner1.close();
            scanner2.close();
            scanner3.close();
        }
    }






























//
//    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//
//    public static void main(String[] args) {
//        try (Scanner scanner = new Scanner(System.in)) {
//            Employee employeeConsole;
//            Employee employeeConsoleId;
//            Employee employeeConsolePin;
//            EmployeeAddress employeePermanentAddressConsole;
//            EmployeeAddress employeeTemporaryAddressConsole;
//            List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
//
//            InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
//            Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
//            Validation validation = new Validation();
//            try {
//                System.out.println(resourceBundle.getString("greet.user"));
//                List<org.dbRepository.Employee> employees = new ArrayList<>();
//                while (true) {
//                    boolean validate = false;
//                    System.out.println(resourceBundle.getString("menu.display"));
//                    System.out.println(resourceBundle.getString("enter.choice"));
//                    int choice = 0;
//                    do {
//                        try {
//                            choice = scanner.nextInt();
//                            validate = true;
//                        }
//                        // checking for input format
//                        catch (InputMismatchException inputMismatchException) {
//                            System.out.println(resourceBundle.getString("Enter.number1"));
//                            scanner.nextLine();
//                        }
//                    } while (!validate);
//                    switch (choice) {
//                        case 1:
//                            do {
//                                System.out.println(resourceBundle.getString("enter.employeeDetails"));
//                                System.out.print(resourceBundle.getString("Enter.name"));
//                                scanner.nextLine();
//                                String name = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.id"));
//                                String id = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.emailId"));
//                                String email = scanner.nextLine();
//
//                                if (!validation.isValidEmail(email)) {
//                                    System.out.println(resourceBundle.getString("invalid.email"));
//                                    break;
//                                }
//
//                                System.out.print(resourceBundle.getString("enter.phone"));
//                                long phoneNumber = Long.parseLong(scanner.nextLine());
//
//                                if (!validation.isValidPhoneNumber(phoneNumber)) {
//                                    System.out.println(resourceBundle.getString("invalid.Phone"));
//                                    break;
//                                }
//
//                                System.out.println(resourceBundle.getString("enter.permanentAddress"));
//                                System.out.print(resourceBundle.getString("enter.street"));
//                                String permanentStreet = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.HouseName"));
//                                String permanentHouseName = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.city"));
//                                String permanentCity = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.state"));
//                                String permanentState = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.pincode"));
//                                int permanentPinCode = Integer.parseInt(scanner.nextLine());
//
//                                if (!validation.isValidPin(permanentPinCode)) {
//                                    System.out.println(resourceBundle.getString("invalid.Pin"));
//                                    break;
//                                }
//
//                                System.out.println(resourceBundle.getString("enter.temporaryaddress"));
//                                System.out.print(resourceBundle.getString("enter.street"));
//                                String temporaryStreet = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.HouseName"));
//                                String temporaryHouseName = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.city"));
//                                String temporaryCity = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.state"));
//                                String temporaryState = scanner.nextLine();
//
//                                System.out.print(resourceBundle.getString("enter.pincode"));
//                                int temporaryPinCode = Integer.parseInt(scanner.nextLine());
//
//                                if (!validation.isValidPin(temporaryPinCode)) {
//                                    System.out.println(resourceBundle.getString("invalid.Pin"));
//                                    break;
//                                }
//
//                                employeeConsole = new Employee(name, id, email, phoneNumber);
//                                employeePermanentAddressConsole = new EmployeeAddress(permanentStreet, permanentHouseName, permanentState, permanentCity, permanentPinCode);
//                                employeeTemporaryAddressConsole = new EmployeeAddress(temporaryStreet, temporaryHouseName, temporaryState, temporaryCity, temporaryPinCode);
//                                employeeConsole = new Employee(employeeConsole, employeePermanentAddressConsole, employeeTemporaryAddressConsole);
//
//                                org.dbRepository.Employee employee;
//                                employee = translateEmployee(employeeConsole);
//
//                                List<org.dbRepository.Employee> employeeInfo = new ArrayList<>();
//                                employeeInfo.add(employee);
//                                inputEmployeeDetails.create(employeeInfo);
//
//                                System.out.print(resourceBundle.getString("add.more"));
//                            } while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
//                            break;
//                        case 2:
//                            //find by id
//                            System.out.println(resourceBundle.getString("enter.id"));
//                            String employeeId = scanner.next();
//                            try {
//                                org.dbRepository.Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
//                                employeeConsoleId = translate2(employee);
//                                System.out.println(employeeConsoleId.displayEmployeeDetails());
//                            } catch (EmployeeNotFoundException e) {
//                                System.out.println(e.getMessage());
//                                logger.warn(e.getMessage());
//                            }
//                            break;
//
//                        case 3:
//                            //display all
//                            List<org.dbRepository.Employee> employeesList = inputEmployeeDetails.read();
//                            if (!employeesList.isEmpty()) {
//                                for (org.dbRepository.Employee emp : employeesList) {
//                                    employeeConsole = translate2(emp);
//                                    System.out.println(employeeConsole.displayEmployeeDetails());
//                                }
//                            } else {
//                                System.out.println(resourceBundle.getString("employee.not.found"));
//                                logger.warn(resourceBundle.getString("employee.not.found"));
//                            }
//                            break;
//                        case 4:
//                            //find by pincode
//                            System.out.println(resourceBundle.getString("enter.pincode"));
//                            int pinCode = scanner.nextInt();
//                            try {
//                                org.dbRepository.Employee employee = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
//                                employeeConsolePin = translate2(employee);
//                                System.out.println(employeeConsolePin.displayEmployeeDetails());
//                            } catch (EmployeeNotFoundException e) {
//                                System.out.println(e.getMessage());
//                                logger.warn(e.getMessage());
//                            }
//                            break;
//                        case 5:
//                            System.exit(0);
//                    }
//                }
//            } finally {
//                // Close connections
//                inputEmployeeDetails.closeConnections();
//                scanner.close();
//            }
//        }
//    }
//
//    private static org.console.EmployeeDetails translate2(EmployeeDetails employeeDetails) {
//
//        Employee employeeConsole = new Employee();
//        EmployeeAddress temporaryAddress = new EmployeeAddress();
//        EmployeeAddress permanentAddress = new EmployeeAddress();
//
//        employeeConsole.setEmployeeName(employeeConsole.getEmployeeName());
//        employeeConsole.setEmployeeId(employeeConsole.getEmployeeId());
//        employeeConsole.setEmailId(employeeConsole.getEmailId());
//        employeeConsole.setPhoneNumber(employeeConsole.getPhoneNumber());
//
//        permanentAddress.setStreet(employeeDetails.getEmployeePermanentAddress().getStreet());
//        permanentAddress.setHouseName(employeeDetails.getEmployeePermanentAddress().getHouseName());
//        permanentAddress.setCity(employeeDetails.getEmployeePermanentAddress().getCity());
//        permanentAddress.setState(employeeDetails.getEmployeePermanentAddress().getState());
//        permanentAddress.setPinCode(employeeDetails.getEmployeePermanentAddress().getPinCode());
//
//        temporaryAddress.setStreet(employeeDetails.getEmployeeTemporaryAddress().getStreet());
//        temporaryAddress.setHouseName(employeeDetails.getEmployeeTemporaryAddress().getHouseName());
//        temporaryAddress.setCity(employeeDetails.getEmployeeTemporaryAddress().getCity());
//        temporaryAddress.setState(employeeDetails.getEmployeeTemporaryAddress().getState());
//        temporaryAddress.setPinCode(employeeDetails.getEmployeeTemporaryAddress().getPinCode());
//
//        return new EmployeeDetails(employeeConsole, permanentAddress, temporaryAddress);
//    }
//
//
//    private static org.dbRepository.Employee translateEmployee(Employee employeeConsole) {
//        org.dbRepository.Employee employee = new org.dbRepository.Employee();
//        employee.setEmployeeName(employeeConsole.getEmployeeName());
//        employee.setEmployeeId(employeeConsole.getEmployeeId());
//        employee.setEmailId(employeeConsole.getEmailId());
//        employee.setPhoneNumber(employeeConsole.getPhoneNumber());
//        return employee;
//
//    }
//
//    private static org.dbRepository.EmployeeAddress translateEmployeeAddress(EmployeeAddress employeeAddressConsole) {
//        org.dbRepository.EmployeeAddress employeeAddress =new org.dbRepository.EmployeeAddress();
//        employeeAddress.setStreet(employeeAddressConsole.getStreet());
//        employeeAddress.setHouseName(employeeAddressConsole.getHouseName());
//        employeeAddress.setCity(employeeAddressConsole.getCity());
//        employeeAddress.setState(employeeAddressConsole.getState());
//        employeeAddress.setPinCode(employeeAddressConsole.getPinCode());
//        return employeeAddress;
//
//    }
}



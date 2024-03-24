package org.console;
import org.dbRepository.DatabaseRepositoryImplementation;
import org.dbRepository.Employee;
import org.dbRepository.EmployeeAddress;
import org.dbRepository.InputEmployeeDetails;
import org.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ConsoleApp {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EmployeeConsole employeeConsole;
            EmployeeConsole employeeConsoleId;
            EmployeeConsole employeeConsolePin;
            EmployeeAddressConsole employeePermanentAddressConsole;
            EmployeeAddressConsole employeeTemporaryAddressConsole;

            InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
            Logger logger= LoggerFactory.getLogger(ConsoleApp.class);
            Validation validation=new Validation();
            try {
                System.out.println(resourceBundle.getString("greet.user"));
                List<Employee> employees = new ArrayList<>();
                while (true) {
                    boolean validate=false;
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    int choice=0;
                    do {
                        try {
                            choice = scanner.nextInt();
                            validate = true;
                        }
                        // checking for input format
                        catch (InputMismatchException inputMismatchException) {
                            System.out.println(resourceBundle.getString("Enter.number1"));
                            scanner.nextLine();
                        }
                    }while (!validate);
                    switch (choice) {
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
                            String permanentStreet = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.HouseName"));
                            String permanentHouseName= scanner.nextLine();

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
                            System.out.print(resourceBundle.getString("enter.street"));
                            String temporaryStreet = scanner.nextLine();

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

                            employeeConsole= new EmployeeConsole(name, id, email, phoneNumber);
                            employeePermanentAddressConsole=new EmployeeAddressConsole(permanentStreet, permanentHouseName, permanentState, permanentCity, permanentPinCode);
                            employeeTemporaryAddressConsole=new EmployeeAddressConsole(temporaryStreet, temporaryHouseName, temporaryState, temporaryCity, temporaryPinCode);
                            employeeConsole=new EmployeeConsole(employeeConsole,employeePermanentAddressConsole,employeeTemporaryAddressConsole);

                            Employee employee;
                            employee=translateEmployee(employeeConsole);

                            List<Employee> employeeInfo = new ArrayList<>();
                            employeeInfo.add(employee);
                            inputEmployeeDetails.create(employeeInfo);

                            System.out.print(resourceBundle.getString("add.more"));
                        }while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                            break;
                        case 2:
                            //find by id
                            System.out.println(resourceBundle.getString("enter.id"));
                            String employeeId = scanner.next();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
                                employeeConsoleId=translate2(employee);
                                System.out.println(employeeConsoleId.displayEmployeeDetails());
                            } catch (EmployeeNotFoundException e) {
                                System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                            }
                            break;

                        case 3:
                            //display all
                            List<Employee> employeesList = inputEmployeeDetails.read();
                            if (!employeesList.isEmpty()) {
                                for (Employee emp : employeesList) {
                                    employeeConsole = translate2(emp);
                                    System.out.println(employeeConsole.displayEmployeeDetails());
                                }
                            } else {
                                System.out.println(resourceBundle.getString("employee.not.found"));
                                logger.warn(resourceBundle.getString("employee.not.found"));
                            }
                            break;
                        case 4:
                            //find by pincode
                            System.out.println(resourceBundle.getString("enter.pincode"));
                            int pinCode = scanner.nextInt();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
                                employeeConsolePin=translate2(employee);
                                System.out.println(employeeConsolePin.displayEmployeeDetails());
                            } catch (EmployeeNotFoundException e) {
                                System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                            }
                            break;
                        case 5:
                            System.exit(0);
                    }
                }
            } finally {
                // Close connections
                inputEmployeeDetails.closeConnections();
                scanner.close();
            }
        }
    }

    private static EmployeeConsole translate2(Employee employee) {

        EmployeeConsole employeeConsole=new EmployeeConsole();
        EmployeeAddressConsole tempAddress=new EmployeeAddressConsole();
        EmployeeAddressConsole permAddress=new EmployeeAddressConsole();

        employeeConsole.setEmployeeName(employee.getEmployeeName());
        employeeConsole.setEmployeeId(employee.getEmployeeId());
        employeeConsole.setEmailId(employee.getEmailId());
        employeeConsole.setPhoneNumber(employee.getPhoneNumber());

        permAddress.setStreet(employee.getEmployeePermanentAddress().getStreet());
        permAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        permAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        permAddress.setState(employee.getEmployeePermanentAddress().getState());
        permAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        tempAddress.setStreet(employee.getEmployeeTemporaryAddress().getStreet());
        tempAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        tempAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        tempAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        tempAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());

        return new EmployeeConsole(employeeConsole,permAddress,tempAddress);
    }


    private static Employee translateEmployee(EmployeeConsole employeeConsole) {
        EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
        EmployeeAddress employeePermanentAddress = new EmployeeAddress();
        Employee employeeBasicDetails = new Employee();
        employeeBasicDetails.setEmployeeName(employeeConsole.getEmployeeName());
        employeeBasicDetails.setEmployeeId(employeeConsole.getEmployeeId());
        employeeBasicDetails.setEmailId(employeeConsole.getEmailId());
        employeeBasicDetails.setPhoneNumber(employeeConsole.getPhoneNumber());


        employeePermanentAddress.setStreet(employeeConsole.getEmployeePermanentAddress().getStreet());
        employeePermanentAddress.setHouseName(employeeConsole.getEmployeePermanentAddress().getHouseName());
        employeePermanentAddress.setCity(employeeConsole.getEmployeePermanentAddress().getCity());
        employeePermanentAddress.setState(employeeConsole.getEmployeePermanentAddress().getState());
        employeePermanentAddress.setPinCode(employeeConsole.getEmployeePermanentAddress().getPinCode());

        employeeTemporaryAddress.setStreet(employeeConsole.getEmployeeTemporaryAddress().getStreet());
        employeeTemporaryAddress.setHouseName(employeeConsole.getEmployeeTemporaryAddress().getHouseName());
        employeeTemporaryAddress.setCity(employeeConsole.getEmployeeTemporaryAddress().getCity());
        employeeTemporaryAddress.setState(employeeConsole.getEmployeeTemporaryAddress().getState());
        employeeTemporaryAddress.setPinCode(employeeConsole.getEmployeeTemporaryAddress().getPinCode());

        return new Employee(employeeBasicDetails,employeePermanentAddress,employeeTemporaryAddress);

    }




//private static EmployeeAddress translateEmployeePermanent(EmployeeAddressConsole employeeAddressConsole){
//    EmployeeAddress employeeAddress = new EmployeeAddress();
//    employeeAddress.setStreet(employeeAddressConsole.getStreet());
//    employeeAddress.setHouseName(employeeAddressConsole.getHouseName());
//    employeeAddress.setCity(employeeAddressConsole.getCity());
//    employeeAddress.setState(employeeAddressConsole.getState());
//    employeeAddress.setPinCode(employeeAddressConsole..getPinCode());
//    return  employeeAddress;
//}
//    private static EmployeeAddress translateEmployeeTemporary(EmployeeConsole employeeConsole) {
//        EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
//        employeeTemporaryAddress.setStreet(employeeConsole.getEmployeeTemporaryAddress().getStreet());
//        employeeTemporaryAddress.setHouseName(employeeConsole.getEmployeeTemporaryAddress().getHouseName());
//        employeeTemporaryAddress.setCity(employeeConsole.getEmployeeTemporaryAddress().getCity());
//        employeeTemporaryAddress.setState(employeeConsole.getEmployeeTemporaryAddress().getState());
//        employeeTemporaryAddress.setPinCode(employeeConsole.getEmployeeTemporaryAddress().getPinCode());
//       return employeeTemporaryAddress;
//    }

}
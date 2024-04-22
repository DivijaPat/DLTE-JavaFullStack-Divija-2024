package org.springjdbc.employee.console;

import link.employee.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springjdbc.employee.Configuration.WebServiceConfiguration;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeConsole {
    private static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebServiceConfiguration.class);
        context.refresh();

        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(resourceBundle.getString("menu"));
            System.out.println(resourceBundle.getString("emp.create"));
            System.out.println(resourceBundle.getString("emp.id"));
            System.out.println(resourceBundle.getString("emp.list"));
            System.out.println(resourceBundle.getString("emp.pin"));
            System.out.println(resourceBundle.getString("emp.exit"));
            System.out.print(resourceBundle.getString("enter.choice"));

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    create(webServiceTemplate, scanner);

                    break;
                case 2:
                    displayById(webServiceTemplate, scanner);
                    logger.info("option 2");
                    break;
                case 3:
                    displayAll(webServiceTemplate);
                    logger.info("option 3");
                    break;
                case 4:
                    displayByPinCode(webServiceTemplate, scanner);
                    logger.info("option 5");
                    break;
                case 5:
                    System.out.println(resourceBundle.getString("exiting"));
                    context.close();
                    System.exit(0);
                default:
                    System.out.println(resourceBundle.getString("invalid.choice"));
            }
        }
    }

    private static void create(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        boolean addMoreEmployees = true;

        while (addMoreEmployees) {
            System.out.println(resourceBundle.getString("enter.employee"));
            System.out.print(resourceBundle.getString("enter.id"));
            String employeeId = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.name"));
            String employeeName = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.email"));
            String email = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.phoneNumber"));
            Long phoneNumber = scanner.nextLong();
            scanner.nextLine(); // Consume newline character

            System.out.println(resourceBundle.getString("enter.permanentDetails"));
            System.out.print(resourceBundle.getString("enter.street"));
            String permStreet = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.houseName"));
            String permHouseName = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.city"));
            String permCity = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.state"));
            String permState = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.pinCode"));
            Integer permPinCode = scanner.nextInt();
            scanner.nextLine();

            System.out.println(resourceBundle.getString("enter.temporaryAddress"));
            System.out.print(resourceBundle.getString("enter.street"));
            String tempStreet = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.houseName"));
            String tempHouseName = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.city"));
            String tempCity = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.state"));
            String tempState = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.pinCode"));
            Integer tempPinCode = scanner.nextInt();
            scanner.nextLine();

            Employeedetails basicDetails = new Employeedetails();
            basicDetails.setEmployeeID(employeeId);
            basicDetails.setEmployeeName(employeeName);
            basicDetails.setEmailID(email);
            basicDetails.setPhoneNumber(phoneNumber);

            Employeeaddress permanentAddress = new Employeeaddress();
            permanentAddress.setStreet(permStreet);
            permanentAddress.setHouseName(permHouseName);
            permanentAddress.setCity(permCity);
            permanentAddress.setState(permState);
            permanentAddress.setPin(permPinCode);

            Employeeaddress temporaryAddress = new Employeeaddress();
            temporaryAddress.setStreet(tempStreet);
            temporaryAddress.setHouseName(tempHouseName);
            temporaryAddress.setCity(tempCity);
            temporaryAddress.setState(tempState);
            temporaryAddress.setPin(tempPinCode);

            link.employee.Employee employee = new link.employee.Employee();
            employee.setEmployeeDetails(basicDetails);
            employee.setEmployeePermanentAddress(permanentAddress);
            employee.setEmployeeTemporaryAddress(temporaryAddress);

            NewEmployeeRequest request = new NewEmployeeRequest();
            request.setEmployee(employee);

            NewEmployeeResponse response = (NewEmployeeResponse) webServiceTemplate.marshalSendAndReceive(request);
            ServiceStatus status = response.getServiceStatus();
            System.out.println(resourceBundle.getString("create.status") + status.getStatus());
            logger.info(resourceBundle.getString("create.status"));
            System.out.println(resourceBundle.getString("create.message") + status.getMessage());
            logger.info(resourceBundle.getString("create.message"));

            System.out.print(resourceBundle.getString("add.another.employee"));
            String choice = scanner.next();
            if (!"yes".equalsIgnoreCase(choice)) {
                addMoreEmployees = false;
            }
            scanner.nextLine();
        }
    }

    private static void displayById(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        System.out.print(resourceBundle.getString("filter.id"));
        String employeeId = scanner.nextLine();
        DisplayBasedOnIdRequest request = new DisplayBasedOnIdRequest();
        request.setEmployeeid(employeeId);
        DisplayBasedOnIdResponse response = (DisplayBasedOnIdResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("id.response") + status.getStatus());
        System.out.println(resourceBundle.getString("id.message") + status.getMessage());
        link.employee.Employee filteredEmployee = response.getEmployee();
        displayEmployeeDetails(filteredEmployee);

    }

    private static void displayAll(WebServiceTemplate webServiceTemplate) {
        DisplayAllRequest request = new DisplayAllRequest();
        DisplayAllResponse response = (DisplayAllResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("filter.idResponse") + status.getStatus());
        System.out.println(resourceBundle.getString("filter.idMessage") + status.getMessage());
        List<link.employee.Employee> employees = response.getEmployee();
        for (link.employee.Employee employee : employees) {
            displayEmployeeDetails(employee);
            System.out.println("---------------------------");

        }
    }

    private static void displayByPinCode(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        System.out.print(resourceBundle.getString("enter.pinCodeFilter"));
        int pinCode = scanner.nextInt();
        scanner.nextLine();
        DisplayBasedOnPinRequest request = new DisplayBasedOnPinRequest();
        request.setPin(pinCode);
        DisplayBasedOnPinResponse response = (DisplayBasedOnPinResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("pinCode.response") + status.getStatus());
        System.out.println(resourceBundle.getString("pinCode.message") + status.getMessage());
        List<Employee> employees = response.getEmployee();
        for (link.employee.Employee employee : employees) {
            displayEmployeeDetails(employee);
            System.out.println("---------------------------");
        }

    }

    private static void displayEmployeeDetails(Employee employee) {
        Employeedetails basicDetails = employee.getEmployeeDetails();
        Employeeaddress permanentAddress = employee.getEmployeePermanentAddress();
        Employeeaddress temporaryAddress = employee.getEmployeeTemporaryAddress();

        System.out.println("\nEmployee Details:");
        System.out.println("Employee ID: " + basicDetails.getEmployeeID());
        System.out.println("Employee Name: " + basicDetails.getEmployeeName());
        System.out.println("Email: " + basicDetails.getEmailID());
        System.out.println("Phone Number: " + basicDetails.getPhoneNumber());

        System.out.println("\nPermanent Address:");
        System.out.println("Address: " + permanentAddress.getStreet());
        System.out.println("House Number: " + permanentAddress.getHouseName());
        System.out.println("City: " + permanentAddress.getCity());
        System.out.println("State: " + permanentAddress.getState());
        System.out.println("Pin Code: " + permanentAddress.getPin());

        System.out.println("\nTemporary Address:");
        System.out.println("Address: " + temporaryAddress.getStreet());
        System.out.println("House Number: " + temporaryAddress.getHouseName());
        System.out.println("City: " + temporaryAddress.getCity());
        System.out.println("State: " + temporaryAddress.getState());
        System.out.println("Pin Code: " + temporaryAddress.getPin());
    }

}
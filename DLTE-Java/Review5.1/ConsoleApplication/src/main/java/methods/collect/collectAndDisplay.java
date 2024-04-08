package methods.collect;

import org.console.Employee;
import org.console.EmployeeAddress;
import org.console.EmployeeDetails;
import org.console.Validation;
import org.dbRepository.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.ResourceBundle;
import java.util.Scanner;

public class collectAndDisplay {
    static Logger logger= LoggerFactory.getLogger("App.class");//import from org.slf4j
    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    Scanner scanner2=new Scanner(System.in);
    Scanner scanner3=new Scanner(System.in);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    public void callCollectDetails(InputEmployeeDetails employeeDetails){
        EmployeeDetails employee=new EmployeeDetails();
        EmployeeAddress temporaryEmployeeAddress=new EmployeeAddress();
        EmployeeAddress permanentEmployeeAddress=new EmployeeAddress();
        Validation validation=new Validation();
        logger.info("Enter data to employee list");
        logger.isEnabledForLevel(Level.INFO);
        do {
            EmployeeDetails employeeDetails = new Employee();
            System.out.println(resourceBundle.getString("employee.id"));
            try {
                int empID = scanner.nextInt();
                if (employeeDetails.doesEmployeeExists(empID)) {
                    throw new EmployeeException();
                } else {
                    employeeBasicDetails1.setEmployeeID(empID);
                }
            } catch (EmployeeException e) {
                System.out.println(resourceBundle.getString("employee.exists"));
                break;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("number.only"));
                break;
            }
            System.out.println(resourceBundle.getString("full.name"));
            System.out.println(resourceBundle.getString("first.name"));
            employeeBasicDetails1.setFirstName(scanner.next());
            System.out.println(resourceBundle.getString("middle.name"));
            employeeBasicDetails1.setMiddleName(scanner.next());
            System.out.println(resourceBundle.getString("last.name"));
            employeeBasicDetails1.setLastName(scanner.next());
            System.out.println(resourceBundle.getString("contact.details"));
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("phone.number"));
                    long phoneNumber = scanner1.nextLong();
                    if (validationData.isPhoneNumberValid(phoneNumber)) {
                        employeeBasicDetails1.setPhoneNumber(phoneNumber);
                        break;
                    } else {
                        throw new EmployeeException();
                    }
                } catch (EmployeeException e) {
                    System.out.println(resourceBundle.getString("invalid.phone.number"));
                } catch (InputMismatchException e) {
                    System.out.println(resourceBundle.getString("number.only"));
                    scanner1.nextLine(); // Consume the invalid input
                }
            }
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("email.id"));
                    String mail = scanner.next();
                    if (validationData.isEmailValid(mail)) {
                        employeeBasicDetails1.setEmailID(mail);
                        break;
                    } else throw new EmployeeException();
                } catch (EmployeeException e) {
                    System.out.println(resourceBundle.getString("email.validation"));
                }
            }
            System.out.println(resourceBundle.getString("temporary.address"));
            System.out.println(resourceBundle.getString("house.name"));
            tempEmployeeAddress1.setHouseName(scanner2.nextLine());
            System.out.println(resourceBundle.getString("street.name"));
            tempEmployeeAddress1.setHouseStreet(scanner2.nextLine());
            System.out.println(resourceBundle.getString("city.name"));
            tempEmployeeAddress1.setCityName(scanner2.nextLine());
            System.out.println(resourceBundle.getString("state.name"));
            tempEmployeeAddress1.setStateName(scanner2.nextLine());
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("pin.code"));
                    int pincode = scanner1.nextInt();
                    if (validationData.isPinCodeValid(pincode)) {
                        tempEmployeeAddress1.setPinCode(pincode);
                        break;
                    } else throw new EmployeeException();
                } catch (EmployeeException e) {
                    System.out.println(resourceBundle.getString("invalid.pincode"));
                } catch (InputMismatchException e) {
                    System.out.println(resourceBundle.getString("number.only"));
                    scanner1.nextLine();
                }
            }
            System.out.println(resourceBundle.getString("permanent.address"));
            System.out.println(resourceBundle.getString("house.name"));
            permEmployeeAddress1.setHouseName(scanner3.nextLine());
            System.out.println(resourceBundle.getString("street.name"));
            permEmployeeAddress1.setHouseStreet(scanner3.nextLine());
            System.out.println(resourceBundle.getString("city.name"));
            permEmployeeAddress1.setCityName(scanner3.nextLine());
            System.out.println(resourceBundle.getString("state.name"));
            permEmployeeAddress1.setStateName(scanner3.nextLine());
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("pin.code"));
                    int pincode = scanner1.nextInt();
                    if (validationData.isPinCodeValid(pincode)) {
                        permEmployeeAddress1.setPinCode(pincode);
                        break;
                    }
                } catch (EmployeeException e) {
                    System.out.println(resourceBundle.getString("invalid.pincode"));
                } catch (InputMismatchException e) {
                    System.out.println(resourceBundle.getString("number.only"));
                    scanner1.nextLine();
                }
            }
            logger.info("Data to be added to array list");
            employee.entity.EmployeeBasicDetails employeeBasicDetails;
            employeeBasicDetails = translateEmployeeBasic(employeeBasicDetails1);
            employee.entity.EmployeeAddress tempEmployeeAddress;
            tempEmployeeAddress = translateEmployeeAddress(tempEmployeeAddress1);
            employee.entity.EmployeeAddress permEmployeeAddress;
            permEmployeeAddress = translateEmployeeAddress(permEmployeeAddress1);
            employee = new employee.entity.Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
            try {
                if(employeeDetails.saveAll(employee) != null) System.out.println("Employee Details added successfully!");
                else System.out.println("Failed to add employee details!");
            }catch(EmployeeExceptions employeeExceptions){
                System.out.println(employeeExceptions.getMessage());
            }
            System.out.println("Do you want to add more?");
        } while (scanner.next().equalsIgnoreCase("yes"));
    }


    public void callDisplayRequired(InputEmployeeDetails employeeDetails){
        int employeeId;
        System.out.println(resourceBundle.getString("employee.id"));
        employeeId = scanner.nextInt();
        try {
            if (employeeDetails.doesEmployeeExists(employeeId)) {
                logger.info("Displaying the info of particular ID:"+employeeId);
                System.out.println(employeeDetails.displayRequired(employeeId));
            } else throw new EmployeeException();
        }catch(EmployeeException e){
            logger.error("Employee with employee ID "+employeeId+"does not exist");
            System.out.println(resourceBundle.getString("employee.doesNotExists"));
        }
    }

    public void callDisplayAll(InputEmployeeDetails employeeDetails){
        logger.info("Displaying all details");
        //System.out.println(employeeDetails.displayAll());
        List<employee.entity.Employee> employee=employeeDetails.displayAll();
        for(employee.entity.Employee employee1:employee) {
            org.consoleEnv.Employee employeeConsole;
            employeeConsole = translateBack(employee1);
            System.out.println(employeeConsole);
        }
    }

    public void callDisplayRequiredPincode(InputEmployeeDetails employeeDetails){
        logger.info("Display required details based on Temporary pincode");
        System.out.println("Enter Temporary Pincode");
        int pincode=scanner.nextInt();
        List<employee.entity.Employee> employeePincode=employeeDetails.displayBasedOnPinCode(pincode);
        for(employee.entity.Employee employee1:employeePincode) {
            org.consoleEnv.Employee employeeConsole;
            employeeConsole = translateBack(employee1);
            System.out.println(employeeConsole);
        }
    }

}

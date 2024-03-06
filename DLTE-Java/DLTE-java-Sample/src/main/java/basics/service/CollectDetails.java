package basics.service;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CollectDetails implements InputEmployeeDetails {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
 public EmployeeDetails collect() {
   Scanner scanner = new Scanner(System.in);
   EmployeeDetails employeeDetails=new EmployeeDetails();
   System.out.println(resourceBundle.getString("First.name"));
   employeeDetails.setFirstName(scanner.next());
   System.out.println(resourceBundle.getString("Middle.name"));
   employeeDetails.setMiddleName(scanner.next());
   System.out.println(resourceBundle.getString("Last.name"));
   employeeDetails.setLastName(scanner.next());
   System.out.println(resourceBundle.getString("Employee.id"));
   employeeDetails.setEmployeeId(scanner.nextInt());
   System.out.println(resourceBundle.getString("email.address"));
   employeeDetails.setEmailAddress(scanner.next());
   System.out.println(resourceBundle.getString("Mobile.number"));
   employeeDetails.setMobileNumber(scanner.nextLong());
   return employeeDetails;
  }

  public EmployeeAddressDetails collectAddress(){
   Scanner scanner= new Scanner(System.in);
   EmployeeAddressDetails employeeAddressDetails=new EmployeeAddressDetails();
   try {
       System.out.println(resourceBundle.getString("Permanent.address"));
       employeeAddressDetails.setPermanentHouseName(scanner.next());
       System.out.println(resourceBundle.getString("Street.name"));
       employeeAddressDetails.setPermanentStreet(scanner.next());
       System.out.println(resourceBundle.getString("City.name"));
       employeeAddressDetails.setPermanentCity(scanner.next());
       System.out.println(resourceBundle.getString("State.name"));
       employeeAddressDetails.setPermanentState(scanner.next());
       System.out.println(resourceBundle.getString("Pin.code"));
       employeeAddressDetails.setPermanentPinCode(scanner.nextInt());
       System.out.println(resourceBundle.getString("Temporary.address"));
       employeeAddressDetails.setTemporaryHouseName(scanner.next());
       System.out.println(resourceBundle.getString("Street.name"));
       employeeAddressDetails.setTemporaryStreet(scanner.next());
       System.out.println(resourceBundle.getString("City.name"));
       employeeAddressDetails.setTemporaryCity(scanner.next());
       System.out.println(resourceBundle.getString("State.name"));
       employeeAddressDetails.setTemporaryState(scanner.next());
       System.out.println(resourceBundle.getString("Pin.code"));
       employeeAddressDetails.setTemporaryPinCode(scanner.nextInt());
   }catch(InputMismatchException inputMismatchException){
       logger.log(Level.WARNING,inputMismatchException.toString());
       System.out.println(resourceBundle.getString("error.message"));
   }
   return employeeAddressDetails;

  }


}


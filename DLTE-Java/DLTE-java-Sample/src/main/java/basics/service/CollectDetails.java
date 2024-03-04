package basics.service;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import java.util.Scanner;

public class CollectDetails {

 public EmployeeDetails collect() {
   Scanner scanner = new Scanner(System.in);
   EmployeeDetails employeeDetails=new EmployeeDetails();
    System.out.println("enter employee first name");
   employeeDetails.setFirstName(scanner.next());
   System.out.println("enter employee Middle name");
   employeeDetails.setMiddleName(scanner.next());
   System.out.println("Enter the last name:");
   employeeDetails.setLastName(scanner.next());
   System.out.println("Enter the employee id:");
   employeeDetails.setEmployeeId(scanner.nextInt());
   System.out.println("Enter the mobile number:");
   employeeDetails.setMobileNumber(scanner.nextLong());
   return employeeDetails;
  }

  public EmployeeAddressDetails collectAddress(){
   Scanner scanner= new Scanner(System.in);
   EmployeeAddressDetails employeeAddressDetails=new EmployeeAddressDetails();
   System.out.println("enter employee permanent address details\n House name:");
   employeeAddressDetails.setPermanentHouseName(scanner.next());
   System.out.println("Street name:");
   employeeAddressDetails.setPermanentStreet(scanner.next());
   System.out.println("City name:");
   employeeAddressDetails.setPermanentCity(scanner.next());
   System.out.println("State name:");
   employeeAddressDetails.setPermanentState(scanner.next());
   System.out.println("Pin Code:");
   employeeAddressDetails.setPermanentPinCode(scanner.nextInt());
   System.out.println("enter employee temporary address details\n House name:");
   employeeAddressDetails.setTemporaryHouseName(scanner.next());
   System.out.println("Street name:");
   employeeAddressDetails.setTemporaryStreet(scanner.next());
   System.out.println("City name:");
   employeeAddressDetails.setTemporaryCity(scanner.next());
   System.out.println("State name:");
   employeeAddressDetails.setTemporaryState(scanner.next());
   System.out.println("Pin Code:");
   employeeAddressDetails.setTemporaryPinCode(scanner.nextInt());

   return employeeAddressDetails;

  }

 public void displayDetails(EmployeeDetails employeeDetail, EmployeeAddressDetails employeeAddressDetail) {
  System.out.println("First name:"+employeeDetail.getFirstName());
  System.out.println("Middle name:"+employeeDetail.getMiddleName());
  System.out.println("Last name:"+employeeDetail.getLastName());
  System.out.println("Employee id:"+employeeDetail.getEmployeeId());
  System.out.println("Mobile number:"+employeeDetail.getMobileNumber());
  System.out.println("\nPermanent Address Details\nHouse name:"+employeeAddressDetail.getPermanentHouseName());
  System.out.println("Street name:"+employeeAddressDetail.getPermanentStreet());
  System.out.println("City name:"+employeeAddressDetail.getPermanentCity());
  System.out.println("State name:"+employeeAddressDetail.getPermanentState());
  System.out.println("Pin Code:"+employeeAddressDetail.getPermanentPinCode());
  System.out.println("\nTemporary Address Details\nHouse name:"+employeeAddressDetail.getTemporaryHouseName());
  System.out.println("Street name:"+employeeAddressDetail.getTemporaryStreet());
  System.out.println("City name:"+employeeAddressDetail.getTemporaryCity());
  System.out.println("State name:"+employeeAddressDetail.getTemporaryState());
  System.out.println("Pin Code:"+employeeAddressDetail.getTemporaryPinCode());

 }
}


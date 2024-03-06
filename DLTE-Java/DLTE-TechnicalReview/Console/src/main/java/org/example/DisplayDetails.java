package org.example;

import java.util.ArrayList;

public class DisplayDetails implements DisplayEmployeeDetails{
    public void displayDetails(ArrayList<EmployeeDetails> employeeDetail, ArrayList<EmployeeAddressDetails> employeeAddressDetail) {

        for(EmployeeDetails each:employeeDetail){
            System.out.println("First name:"+each.getFirstName());
            System.out.println("Middle name:"+each.getMiddleName());
            System.out.println("Last name:"+each.getLastName());
            System.out.println("Email id:"+each.getEmailAddress());
            System.out.println("Employee id:"+each.getEmployeeId());
            System.out.println("Mobile number:"+each.getMobileNumber());
        }
        for (EmployeeAddressDetails each: employeeAddressDetail) {
            System.out.println("\nPermanent Address Details\nHouse name:"+each.getPermanentHouseName());
            System.out.println("Street name:"+each.getPermanentStreet());
            System.out.println("City name:"+each.getPermanentCity());
            System.out.println("State name:"+each.getPermanentState());
            System.out.println("Pin Code:"+each.getPermanentPinCode());
            System.out.println("\nTemporary Address Details\nHouse name:"+each.getTemporaryHouseName());
            System.out.println("Street name:"+each.getTemporaryStreet());
            System.out.println("City name:"+each.getTemporaryCity());
            System.out.println("State name:"+each.getTemporaryState());
            System.out.println("Pin Code:"+each.getTemporaryPinCode());


        }


    }
}

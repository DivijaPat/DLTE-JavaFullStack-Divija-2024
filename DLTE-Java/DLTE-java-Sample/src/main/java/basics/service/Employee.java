package basics.service;

import basics.service.CollectDetails;
import basics.service.EmployeeAddressDetails;
import basics.service.EmployeeDetails;

import java.util.Scanner;
public class Employee {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        CollectDetails collectDetails=new CollectDetails() ;
        System.out.println("-----Welcome-----\nEnter the number of employees:");
        int size = scanner.nextInt();
        EmployeeAddressDetails[] employeeAddressDetails = new EmployeeAddressDetails[size];
        EmployeeDetails[] employeeDetails = new EmployeeDetails[size];
//        for(int index=0;index<size;index++){
//            employeeDetails[index]=collectDetails.collect();
//            employeeAddressDetails[index]=collectDetails.collectAddress();
//        }
//        for(int index=0;index<size;index++){
//            System.out.println("Employee "+(index+1)+" details are:\n");
//            collectDetails.displayDetails(employeeDetails[index],employeeAddressDetails[index]);
//            System.out.println("-----Thank You-----");
//        }
        for(EmployeeDetails each:employeeDetails){
             collectDetails.collect();

        }
     scanner.close();

    }
}

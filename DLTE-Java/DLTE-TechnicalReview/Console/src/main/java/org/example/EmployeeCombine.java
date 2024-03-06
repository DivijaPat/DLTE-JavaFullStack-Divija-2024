package org.example;

public class EmployeeCombine {
        private EmployeeDetails employeeDetails;
        private EmployeeAddressDetails employeeAddressDetails;

    public EmployeeCombine(EmployeeDetails employeeDetails, EmployeeAddressDetails employeeAddressDetails) {
        this.employeeDetails = employeeDetails;
        this.employeeAddressDetails = employeeAddressDetails;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public EmployeeAddressDetails getEmployeeAddressDetails() {
        return employeeAddressDetails;
    }

    public void setEmployeeAddressDetails(EmployeeAddressDetails employeeAddressDetails) {
        this.employeeAddressDetails = employeeAddressDetails;
    }


}



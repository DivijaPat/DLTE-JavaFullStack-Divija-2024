package org.console;

public class EmployeeConsole {
    private String employeeId;
    private String employeeName;
    private String emailId;
    private long phoneNumber;
    EmployeeAddressConsole employeePermanentAddress;
    EmployeeAddressConsole employeeTemporaryAddress;

    public EmployeeConsole(String employeeId,String employeeName, String emailId, long phoneNumber, EmployeeAddressConsole employeePermanentAddress, EmployeeAddressConsole employeeTemporaryAddress) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public EmployeeConsole( String employeeId,String employeeName, String emailId, long phoneNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public EmployeeConsole(EmployeeConsole employee1, EmployeeAddressConsole permanentAddr, EmployeeAddressConsole temporaryAddr) {
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", employeeId='" + employeeId + '\'' +
                "employeeName='" + employeeName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", employeePermanentAddress=" + employeePermanentAddress +
                ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                '}';
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeeAddressConsole getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(EmployeeAddressConsole employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public EmployeeAddressConsole getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(EmployeeAddressConsole employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public EmployeeConsole() {
    }
    public String displayEmployeeDetails() {
        return "Employee ID: " + getEmployeeId() +
                "\nName: " + getEmployeeName() +
                "\nEmail: " + getEmailId() +
                "\nPhone Number: " + getPhoneNumber() +
                "\nPermanent Address: " + employeePermanentAddress.getStreet() +
                "\nPermanent House Name: " + employeePermanentAddress.getHouseName() +
                "\nPermanent City: " + employeePermanentAddress.getCity() +
                "\nPermanent State: " + employeePermanentAddress.getState() +
                "\nPermanent Pin Code: " + employeePermanentAddress.getPinCode() +
                "\nTemporary Address: " + employeeTemporaryAddress.getStreet() +
                "\nTemporary House Name: " + employeeTemporaryAddress.getHouseName() +
                "\nTemporary City: " + employeeTemporaryAddress.getCity() +
                "\nTemporary State: " + employeeTemporaryAddress.getState() +
                "\nTemporary Pin Code: " + employeeTemporaryAddress.getPinCode();
    }
}
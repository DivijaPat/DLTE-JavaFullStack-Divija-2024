package org.console;

public class Employee {
    private String employeeId;
    private String employeeName;
    private String emailId;
    private long phoneNumber;


    public Employee(String employeeId, String employeeName, String emailId, long phoneNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "Employee{" +
                ", employeeId='" + employeeId + '\'' +
                "employeeName='" + employeeName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
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


    public Employee() {
    }
    public String displayEmployeeDetails() {
        return "Employee ID: " + getEmployeeId() +
                "\nName: " + getEmployeeName() +
                "\nEmail: " + getEmailId() +
                "\nPhone Number: " + getPhoneNumber();
    }
}
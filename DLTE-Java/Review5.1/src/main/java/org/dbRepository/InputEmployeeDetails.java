package org.dbRepository;

import java.util.List;

public interface InputEmployeeDetails {
    void create(List<EmployeeDetails> employeeDetails);
    EmployeeDetails displayBasedOnEmployeeId(String employeeID);
    EmployeeDetails displayBasedOnPinCode(int pinCode);
    List<EmployeeDetails> read();
    void closeConnections();
}
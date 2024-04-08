package backend.pojo;

import java.util.List;

public interface InputEmployeeDetails {
    void create(List<Employee> employee);
    List<Employee> displayBasedOnPinCode(int pinCode);
    Employee displayBasedOnId(String employeeID);
    boolean DataValidation(List<Employee> employee);
    List<Employee> read();
    void closeConnections();

}
package org.springjdbc.employee.remotes;


import org.springframework.stereotype.Repository;
import org.springjdbc.employee.entity.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository {
    Employee create(Employee employee);
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pin);
    List<Employee> read();

}

package org.dbRepository;
import org.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public Connection connection;
    ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
    public DatabaseRepositoryImplementation() {
        connection=StorageTarget.createConnection();
        }

    @Override
    public void create(List<Employee> list) {

        for(Employee employee:list){

            String employeeID=employee.getEmployeeId();
            try{
                String employees = "INSERT INTO Employee1 (id, name,email,phoneNumber) VALUES (?, ?,?,?)";
                preparedStatement=connection.prepareStatement(employees);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeName());
                preparedStatement.setString(3,employee.getEmailId());
                preparedStatement.setLong(4,employee.getPhoneNumber());
                int employeeResult=preparedStatement.executeUpdate();

                String permanentaddress = "INSERT INTO EmployeePermanentAddress1 (employeeId,permanentStreet, permanentHouseName,permanentCity, permanentState,permanentPinCode) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(permanentaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeePermanentAddress().getStreet());
                preparedStatement.setString(3,employee.getEmployeePermanentAddress().getHouseName());
                preparedStatement.setString(4,employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeePermanentAddress().getPinCode());
                int resultPermanent=preparedStatement.executeUpdate();

                String temporaryaddress = "INSERT INTO EmployeeTemporaryAddress1(employeeId,temporaryStreet, temporaryHouseName,temporaryCity, temporaryState,temporaryPinCode) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(temporaryaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeTemporaryAddress().getStreet());
                preparedStatement.setString(3,employee.getEmployeeTemporaryAddress().getHouseName());
                preparedStatement.setString(4,employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeeTemporaryAddress().getPinCode());
                int resultTemporary=preparedStatement.executeUpdate();

                System.out.println(resourceBundle1.getString("employee.add")+" " + employeeID +" "+resourceBundle1.getString("employeeAdd.success"));

            }catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.error(resourceBundle1.getString("Fail.insert")+" "+employeeID  + " "+resourceBundle1.getString("employee.exists"));
                    System.out.println(resourceBundle1.getString("Fail.insert") +" "+ employeeID + " "+resourceBundle1.getString("employee.exists"));

                } else {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public Employee displayBasedOnEmployeeId(String employeeID) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM employee1 emp INNER JOIN EmployeePermanentAddress1 empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress1 empTAdd ON emp.id = empTAdd.employeeId WHERE emp.id = ?";
            preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,employeeID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee1 = new Employee(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentStreet"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryStreet"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(employee1, permanentAddr, temporaryAddr);
            }else {
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.employee") + employeeID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee displayBasedOnPinCode(int pinCode) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM employee1 emp INNER JOIN EmployeePermanentAddress1 empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress1 empTAdd ON emp.id = empTAdd.employeeId WHERE empPAdd.permanentPinCode = ? OR empTAdd.temporaryPinCode = ?";
            preparedStatement =connection.prepareStatement(query);
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee basicDetails = new Employee(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentStreet"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryStreet"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(basicDetails, permanentAddress, temporaryAddress);
            }else {
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.pincode")+ pinCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try {
            String readAll = "SELECT * FROM employee1 emp INNER JOIN EmployeePermanentAddress1 empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress1 empTAdd ON emp.id = empTAdd.employeeId";
            preparedStatement = connection.prepareStatement(readAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                Employee basicDetails = new Employee(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentStreet"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryStreet"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(basicDetails, permanentAddress, temporaryAddress);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void closeConnections() {
        try {
            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

            if (connection != null)
                connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
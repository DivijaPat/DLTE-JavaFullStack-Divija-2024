package org.example;
import exception.EmployeeNotFoundException;
import oracle.jdbc.driver.OracleDriver;
import org.example.Details.Employee;
import org.example.Details.EmployeeAddress;
import org.example.Details.EmployeeBasicDetails;
import org.example.Details.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {

    Connection connection;
    ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Logger logger= LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
    ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    public DatabaseRepositoryImplementation() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public boolean isEstablished(){
//        return connection!=null;
//    }
    @Override
    public void create(List<Employee> list) {

        for(Employee employee:list){

            String employeeID=employee.getEmployeeBasicDetails().getEmployeeId();
            try{
                String employees = "INSERT INTO Employee (id, name) VALUES (?, ?)";
                preparedStatement=connection.prepareStatement(employees);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeBasicDetails().getEmployeeName());
                int resultBasic=preparedStatement.executeUpdate();

                String permanentaddress = "INSERT INTO EmployeeAddress (employeeId,permanentAddress, permanentHouseName,permanentCity, permanentState,permanentPinCode) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(permanentaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeePermanentAddress().getAddress());
                preparedStatement.setString(3,employee.getEmployeePermanentAddress().getHouseName());
                preparedStatement.setString(4,employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeePermanentAddress().getPinCode());
                int resultPermanent=preparedStatement.executeUpdate();

                String temporaryaddress = "INSERT INTO EmployeeTemporaryAddress(employeeId,temporaryAddress, temporaryHouseName,temporaryCity, temporaryState,temporaryPinCode) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(temporaryaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeTemporaryAddress().getAddress());
                preparedStatement.setString(3,employee.getEmployeeTemporaryAddress().getHouseName());
                preparedStatement.setString(4,employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeeTemporaryAddress().getPinCode());
                int resultTemporary=preparedStatement.executeUpdate();

                String information = "INSERT INTO EmployeeInformation (employeeId, email, phoneNumber) VALUES (?, ?, ?)";
                preparedStatement=connection.prepareStatement(information);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeBasicDetails().getEmailId());
                preparedStatement.setLong(3,employee.getEmployeeBasicDetails().getPhoneNumber() );
                int resultInformation=preparedStatement.executeUpdate();

                System.out.println(resourceBundle1.getString("employee.add")+" " + employeeID +" "+resourceBundle1.getString("employeeAdd.success"));

            }catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.error(resourceBundle.getString("Fail.insert")+" "+employeeID  + " "+resourceBundle1.getString("employee.exists"));
                    System.out.println(resourceBundle1.getString("Fail.insert") +" "+ employeeID + " "+resourceBundle1.getString("employee.exists"));

                } else {
                    e.printStackTrace();
                }
            }

        }

    }


    @Override
    public Employee displayBasedOnEmployeeId(String employeeId) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM employee emp " +
                    "INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId " +
                    "INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId " +
                    "INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId " +
                    "WHERE emp.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
            }else {
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.employee") + employeeId);
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
            String query = "SELECT * FROM employee emp INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId WHERE empPAdd.permanentPinCode = ? OR empTAdd.temporaryPinCode = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
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
            String readAll = "SELECT * FROM employee emp INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId";
            preparedStatement = connection.prepareStatement(readAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );
                EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );
                employee = new Employee(basicDetails, permanentAddress, temporaryAddress);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


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
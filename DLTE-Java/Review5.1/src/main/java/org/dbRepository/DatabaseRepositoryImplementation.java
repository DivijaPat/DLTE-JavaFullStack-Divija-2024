package org.dbRepository;
import org.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {

    public Connection connection;

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Validation validation=new Validation();
    Logger logger= LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
    ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    public DatabaseRepositoryImplementation() {
        connection= StorageTarget.createConnection();
    }
    @Override
    public List create(List list) {
        List createdEmployees = new ArrayList<>();
        Validation validation = new Validation();
        validation.Validation(list);
        for (EmployeeDetails employee : list) {
            String employeeID = employee.getEmployee().getEmployeeId();
            try {
                connection = StorageTarget.createConnection();
                String employees = "INSERT INTO Employee (EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(employees);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployee().getEmployeeName());
                preparedStatement.setString(3, employee.getEmployee().getEmailId());
                preparedStatement.setLong(4, employee.getEmployee().getPhoneNumber());
                preparedStatement.executeUpdate();

                // Inserting into Address table
                String insertTemporaryAddress = "insert into EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,1)";
                preparedStatement = connection.prepareStatement(insertTemporaryAddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeeTemporaryAddress().getStreet());
                preparedStatement.setString(3, employee.getEmployeeTemporaryAddress().getHouseName());
                preparedStatement.setString(4, employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeeTemporaryAddress().getPinCode());
                preparedStatement.executeUpdate();

                String insertPermanentAddress = "insert into EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,0)";
                preparedStatement = connection.prepareStatement(insertPermanentAddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeePermanentAddress().getStreet());
                preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseName());
                preparedStatement.setString(4, employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeePermanentAddress().getPinCode());
                preparedStatement.executeUpdate();

                createdEmployees.add(employee); // Add the created employee to the list of created employees
                System.out.println(resourceBundle1.getString("employee.add") + employeeID +" "+resourceBundle1.getString("employeeAdd.success"));
                logger.info(resourceBundle1.getString("employee.add")+ employeeID +" "+resourceBundle1.getString("employeeAdd.success"));
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println(resourceBundle1.getString("Fail.insert") + " " + employeeID + " " + resourceBundle1.getString("employee.exists"));
                    logger.error(resourceBundle1.getString("Fail.insert") + " " + employeeID + " " + resourceBundle1.getString("employee.exists"));
                } else {
                    e.printStackTrace();
                }
            } finally {
                closeConnections();
            }
        }
        return createdEmployees;

    }


    @Override
    public EmployeeDetails displayBasedOnEmployeeId(String employeeId) {
        EmployeeDetails employee =new EmployeeDetails();
        try {
            connection=StorageTarget.createConnection();
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ad.HOUSENAME,ad.STREETNAME,ad.CITYNAME,ad.STATENAME,ad.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "                             INNER JOIN Address ad ON emp.EMPLOYEEID = ad.EMPLOYEEID AND ad.ISTEMPORARY = 1\n" +
                    "                             INNER JOIN Address pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "                             WHERE emp.EMPLOYEEID=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee basicDetails = new Employee(
                        resultSet.getString("EmployeeName"),
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("emailId"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getInt(14)
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
                );

                employee = new EmployeeDetails(basicDetails, permanentAddr, temporaryAddr);
            }else{
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.employee") + employeeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return employee;
    }

    @Override
    public List displayBasedOnPinCode(int pinCode) {
        List employees = new ArrayList<>();
        try (Connection connection = StorageTarget.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                             "                             INNER JOIN Address ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                             "                             INNER JOIN Address pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                             "                             WHERE ta.PINCODE=? or pa.PINCODE=?");
        ) {
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employee basicDetails = new Employee(
                            resultSet.getString("EmployeeName"),
                            resultSet.getString("EmployeeId"),
                            resultSet.getString("emailId"),
                            resultSet.getLong("phoneNumber")
                    );

                    EmployeeAddress permanentAddress = new EmployeeAddress(
                            resultSet.getString(10),
                            resultSet.getString(11),
                            resultSet.getString(12),
                            resultSet.getString(13),
                            resultSet.getInt(14)
                    );

                    EmployeeAddress temporaryAddress = new EmployeeAddress(
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getInt(9)
                    );

                    employees.add(new EmployeeDetails(basicDetails, permanentAddress, temporaryAddress));
                }
            }
        } catch (SQLException e) {
            throw new EmployeeNotFoundException(resourceBundle1.getString("no.pincode")+ pinCode);
        }
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(resourceBundle1.getString("no.pincode")+ pinCode);
        }
        return employees;
    }

    @Override
    public List read() {
        List employees = new ArrayList<>();
        try {
            connection=StorageTarget.createConnection();
            String findAll = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "                             INNER JOIN Address ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "                             INNER JOIN Address pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployeeDetails employee = null;
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getInt(14)
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
                );
                Employee basicDetails = new Employee(
                        resultSet.getString("EmployeeName"),
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("emailId"),
                        resultSet.getLong("phoneNumber")
                );
                employee = new EmployeeDetails(basicDetails, permanentAddress, temporaryAddress);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return employees;
    }
    public void closeConnections() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
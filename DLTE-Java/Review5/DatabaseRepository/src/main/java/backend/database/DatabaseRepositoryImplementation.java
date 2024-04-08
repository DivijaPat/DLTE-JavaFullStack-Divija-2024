package backend.database;
import backend.pojo.Employee;
import backend.pojo.EmployeeAddress;
import backend.pojo.EmployeeBasicDetails;
import backend.pojo.InputEmployeeDetails;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        ConnectionTarget connectionTarget=new ConnectionTarget();
    PreparedStatement preparedStatement;
    Connection connection=connectionTarget.ConnectionApp();
    ResultSet resultSet;


    public boolean isEstablished(){
        return connection!=null;
    }
    @Override
    public void create(List<Employee> list) {
        for(Employee employee:list){

            String employeeID=employee.getEmployeeBasicDetails().getId();
            try{
                String employees = "INSERT INTO Employee (EmployeeId,EmployeeName,emailId,phoneNumber) VALUES (?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(employees);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeBasicDetails().getName());
                preparedStatement.setString(3,employee.getEmployeeBasicDetails().getEmail());
                preparedStatement.setLong(4,employee.getEmployeeBasicDetails().getPhoneNumber());
                int resultBasic=preparedStatement.executeUpdate();

                String address = "INSERT INTO Address (EmployeeId,permanentStreet, permanentHouseName,permanentCity, permanentState,permanentPinCode,temporaryStreet,temporaryHouseName,temporaryCity, temporaryState,temporaryPinCode) " +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(address);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeePermanentAddress().getStreet());
                preparedStatement.setString(3,employee.getEmployeePermanentAddress().getHouseName());
                preparedStatement.setString(4,employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeePermanentAddress().getPinCode());
                preparedStatement.setString(7,employee.getEmployeeTemporaryAddress().getStreet());
                preparedStatement.setString(8,employee.getEmployeeTemporaryAddress().getHouseName());
                preparedStatement.setString(9,employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(10,employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(11,employee.getEmployeeTemporaryAddress().getPinCode());
                int resultAddress=preparedStatement.executeUpdate();

                System.out.println(resourceBundle.getString("employee.add")+" " + employeeID +" "+resourceBundle.getString("employeeAdd.success"));

            }catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println(resourceBundle.getString("fail.insert") +" "+ employeeID + " "+resourceBundle.getString("employee.exists"));
                } else {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Employee displayBasedOnId(String employeeId) {
        Employee employee = null;
        try {
            String query = "SELECT e.EmployeeId, e.EmployeeName, e.emailId, e.phoneNumber, a.permanentStreet, a.permanentHouseName, a.permanentCity, a.permanentState, a.permanentPinCode, a.temporaryStreet, a.temporaryHouseName, a.temporaryCity, a.temporaryState, a.temporaryPinCode FROM Employee e INNER JOIN Address a ON e.employeeid = a.EmployeeId WHERE e.employeeid = ?";
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pinCode) {
        List<Employee> employee = null;
        try {
            String query = "SELECT e.EmployeeId,e.EmployeeName,e.emailId,e.phoneNumber,a.permanentStreet,a.permanentHouseName,a.permanentState,a.permanentCity,a.permanentPinCode,a.temporaryStreet,a.temporaryHouseName,a.temporaryState,a.temporaryCity,a.temporaryPinCode FROM employee e  INNER JOIN Address a ON e.EmployeeId = a.EmployeeId WHERE a.permanentPinCode = ? OR a.temporaryPinCode = ?";
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

                employee.add (new Employee(basicDetails, permanentAddress, temporaryAddress));
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
            String findAll = "SELECT e.EmployeeId,e.EmployeeName,e.emailId,e.phoneNumber,a.permanentStreet,a.permanentHouseName,a.permanentState,a.permanentCity,a.permanentPinCode,a.temporaryStreet,a.temporaryHouseName,a.temporaryState,a.temporaryCity,a.temporaryPinCode FROM employee e  INNER JOIN Address a ON e.EmployeeId = a.EmployeeId";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
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
            throw new EmployeeCustomExceptions("system.error");
        }
    }

    @Override
    public boolean DataValidation(List<Employee> employees) {
        for (Employee employee : employees) {
            if (!isValidEmail(employee.getEmployeeBasicDetails().getEmail()) ||
                    !isValidPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber()) ||
                    !isValidPin(employee.getEmployeePermanentAddress().getPinCode()) ||
                    !isValidPin(employee.getEmployeeTemporaryAddress().getPinCode())) {
                return false;
            }
        }
        return true;
    }
    // Validation methods
    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(long phoneNumber) {
        String regex = "0*(\\d{10})"; // Optional zeros followed by 10 digits
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Long.toString(phoneNumber));
        return matcher.matches();
    }
    public static boolean isValidPin(int pin) {
        String pinString = String.valueOf(pin);
        return pinString.length() == 6;
    }
}
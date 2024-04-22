package org.console.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.springjdbc.employee.entity.EmployeeAddress;
import org.springjdbc.employee.entity.EmployeeDetails;
import org.springjdbc.employee.exceptions.EmployeeException;
import org.springjdbc.employee.remotes.EmployeeRepository;

import link.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ComponentScan("org.springjdbc.employee.services")
@Endpoint
public class SoapEndpoint {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(SoapEndpoint.class);
    private final String url = "http://employee.link";
    // http://localhost:8084/employeerepo/employee.wsdl

    @Autowired
    private EmployeeRepository employeeRepository;

    @PayloadRoot(namespace = url, localPart = "newEmployeeRequest")
    @ResponsePayload
    public NewEmployeeResponse createEmployee(@RequestPayload NewEmployeeRequest newEmployeeRequest) throws EmployeeException {
        NewEmployeeResponse employeeResponse = new NewEmployeeResponse();

        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = newEmployeeRequest.getEmployee();
        try {
            org.springjdbc.employee.entity.Employee employee = new org.springjdbc.employee.entity.Employee();
            org.springjdbc.employee.entity.EmployeeDetails daoBasic = new org.springjdbc.employee.entity.EmployeeDetails();
            org.springjdbc.employee.entity.EmployeeAddress daoTempAddress = new org.springjdbc.employee.entity.EmployeeAddress();
            org.springjdbc.employee.entity.EmployeeAddress daoPermAddress = new org.springjdbc.employee.entity.EmployeeAddress();
            daoBasic = copy(actualEmployee.getEmployeeDetails());
            daoPermAddress = copy(actualEmployee.getEmployeePermanentAddress());
            daoTempAddress = copyTemp(actualEmployee.getEmployeeTemporaryAddress());
            employee.setEmployeeDetails(daoBasic);
            employee.setEmployeePermanentAddress(daoPermAddress);
            employee.setEmployeeTemporaryAddress(daoTempAddress);
            employee = employeeRepository.create(employee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            employeeResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage("Employee inserted successfully");
        } catch (EmployeeException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Employee already exists");
        }
        employeeResponse.setServiceStatus(serviceStatus);
        return employeeResponse;
    }

    @PayloadRoot(namespace = url,localPart = "displayAllRequest")
    @ResponsePayload
    public DisplayAllResponse displayAll(@RequestPayload DisplayAllRequest findAllEmployeeRequest) {
        DisplayAllResponse findAllEmployeeResponse=new DisplayAllResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Employee> employees =new ArrayList<>();
        try {

            List<org.springjdbc.employee.entity. Employee> daoEmployee = employeeRepository.read();
            daoEmployee.forEach(each -> {
                Employee employee = new Employee();
                Employeedetails basic = new  Employeedetails();
                Employeeaddress temporaryAddress=new Employeeaddress();
                Employeeaddress permanentAddress=new Employeeaddress();
                basic = copyDisplay(each.getEmployeeDetails());
                permanentAddress = copyDisplayPerm(each.getEmployeePermanentAddress());
                temporaryAddress = copyTempDisplay(each.getEmployeeTemporaryAddress());

                employee.setEmployeeDetails(basic);
                employee.setEmployeePermanentAddress(permanentAddress);
                employee.setEmployeeTemporaryAddress(temporaryAddress);
                employees.add(employee);
            });
            serviceStatus.setStatus( HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee displayed successfully");
        }catch (EmployeeException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage("Employee not found");
        }
        findAllEmployeeResponse.setServiceStatus(serviceStatus);
        findAllEmployeeResponse.getEmployee().addAll(employees);
        return findAllEmployeeResponse;
    }

    @PayloadRoot(namespace = url,localPart = "displayBasedOnIdRequest")
    @ResponsePayload
    public DisplayBasedOnIdResponse displayBasedOnEmployeeId(@RequestPayload DisplayBasedOnIdRequest findAllEmployeeRequest) {
        DisplayBasedOnIdResponse filterByIdResponse = new DisplayBasedOnIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = new Employee();
        try {
            org.springjdbc.employee.entity.Employee employee = employeeRepository.displayBasedOnEmployeeId(findAllEmployeeRequest.getEmployeeid());
            Employeedetails basic = new Employeedetails();
            Employeeaddress temporaryAddress=new Employeeaddress();
            Employeeaddress permanentAddress=new Employeeaddress();
            basic = copyDisplay(employee.getEmployeeDetails());
            permanentAddress = copyDisplayPerm(employee.getEmployeePermanentAddress());
            temporaryAddress = copyTempDisplay(employee.getEmployeeTemporaryAddress());

            actualEmployee.setEmployeeDetails(basic);
            actualEmployee.setEmployeePermanentAddress(permanentAddress);
            actualEmployee.setEmployeeTemporaryAddress(temporaryAddress);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            filterByIdResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage("Employee found successfully");
        } catch (EmployeeException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage("Employee not found");
        }
        filterByIdResponse.setServiceStatus(serviceStatus);
        return filterByIdResponse;
    }

    @PayloadRoot(namespace = url,localPart ="displayBasedOnPinRequest")
    @ResponsePayload
    public DisplayBasedOnPinResponse displayBasedOnPinCode(@RequestPayload DisplayBasedOnPinRequest filterAllEmployeeRequest) {
        DisplayBasedOnPinResponse getEmployeeByPinCodeResponse = new DisplayBasedOnPinResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> employees = new ArrayList<>();
        try {
            List<org.springjdbc.employee.entity.Employee> daoEmployees = employeeRepository.displayBasedOnPinCode(filterAllEmployeeRequest.getPin());
            daoEmployees.forEach(each -> {
                Employee currentEmployee = new Employee();
                Employeedetails basic = new Employeedetails();
                Employeeaddress temporaryAddress=new Employeeaddress();
                Employeeaddress permanentAddress=new Employeeaddress();
                basic = copyDisplay(each.getEmployeeDetails());
                permanentAddress = copyDisplayPerm(each.getEmployeePermanentAddress());
                temporaryAddress = copyTempDisplay(each.getEmployeeTemporaryAddress());
                currentEmployee.setEmployeeDetails(basic);
                currentEmployee.setEmployeePermanentAddress(permanentAddress);
                currentEmployee.setEmployeeTemporaryAddress(temporaryAddress);
                employees.add(currentEmployee);
            });
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee details fetched successfully");
        } catch (EmployeeException employeeException){
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Employee not found");
        }
        getEmployeeByPinCodeResponse.setServiceStatus(serviceStatus);
        getEmployeeByPinCodeResponse.getEmployee().addAll(employees);
        return getEmployeeByPinCodeResponse;
    }
    private EmployeeAddress copy(Employeeaddress employeePermanentAddress){
        EmployeeAddress permAddress = new EmployeeAddress();
        permAddress.setHouseName(employeePermanentAddress.getHouseName());
        permAddress.setStreet(employeePermanentAddress.getStreet());
        permAddress.setCity(employeePermanentAddress.getCity());
        permAddress.setState(employeePermanentAddress.getState());
        permAddress.setPin(employeePermanentAddress.getPin());
        return permAddress;
    }
    private EmployeeAddress copyTemp(Employeeaddress employeeTemporaryAddress){
        EmployeeAddress tempAddress = new EmployeeAddress();
        tempAddress.setHouseName(employeeTemporaryAddress.getHouseName());
        tempAddress.setStreet(employeeTemporaryAddress.getStreet());
        tempAddress.setCity(employeeTemporaryAddress.getCity());
        tempAddress.setState(employeeTemporaryAddress.getState());
        tempAddress.setPin(employeeTemporaryAddress.getPin());
        return tempAddress;
    }
    private EmployeeDetails copy(Employeedetails employeeDetails){
        org.springjdbc.employee.entity.EmployeeDetails employee = new  org.springjdbc.employee.entity.EmployeeDetails();
        employee.setEmployeeId(employeeDetails.getEmployeeID());
        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmailId(employeeDetails.getEmailID());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        return employee;
    }
    private Employeeaddress copyDisplayPerm(EmployeeAddress employeePermanentAddress){
        Employeeaddress permAddress = new Employeeaddress();
        permAddress.setHouseName(employeePermanentAddress.getHouseName());
        permAddress.setStreet(employeePermanentAddress.getStreet());
        permAddress.setCity(employeePermanentAddress.getCity());
        permAddress.setState(employeePermanentAddress.getState());
        permAddress.setPin(employeePermanentAddress.getPin());
        return permAddress;
    }
    private Employeeaddress copyTempDisplay(EmployeeAddress employeeTemporaryAddress){
        Employeeaddress tempAddress = new Employeeaddress();
        tempAddress.setHouseName(employeeTemporaryAddress.getHouseName());
        tempAddress.setStreet(employeeTemporaryAddress.getStreet());
        tempAddress.setCity(employeeTemporaryAddress.getCity());
        tempAddress.setState(employeeTemporaryAddress.getState());
        tempAddress.setPin(employeeTemporaryAddress.getPin());
        return tempAddress;
    }
    private Employeedetails copyDisplay(EmployeeDetails employeeDetails){
        Employeedetails employee = new  Employeedetails();
        employee.setEmployeeID(employeeDetails.getEmployeeId());
        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmailID(employeeDetails.getEmailId());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        return employee;
    }
}


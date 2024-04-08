package webservice;

import backend.database.DatabaseRepositoryImplementation;
import backend.pojo.Employee;
import backend.pojo.InputEmployeeDetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebServiceImplementation {

    private InputEmployeeDetails inputEmployeeDetails;

    @WebMethod
    @WebResult(name = "employee")
    public EmployeeWebService displayBasedOnPinCode(@WebParam(name = "pinCode") int pinCode) {
        InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
        List<Employee> employees = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
        EmployeeWebService employeeWebService=new EmployeeWebService();
        employeeWebService.setEmployees(employees);
        return employeeWebService;
    }
}

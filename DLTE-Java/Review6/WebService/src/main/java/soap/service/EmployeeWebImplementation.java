package soap.service;
import org.databaserepo.database.DatabaseRepositoryImplementation;
import org.databaserepo.database.StorageTarget;
import org.databaserepo.entity.Employee;
import org.databaserepo.exception.EmployeeException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeWebImplementation  {
    private DatabaseRepositoryImplementation databaseRepositoryImplementation;
    public EmployeeWebImplementation(){
        StorageTarget storageTarget=new StorageTarget();
        databaseRepositoryImplementation=new DatabaseRepositoryImplementation();
    }

    @WebResult(name="addNewEmployee")
    @WebMethod
    public Employee callSaveAll(@WebParam Employee employees){
        try {
            List<Employee> employee =databaseRepositoryImplementation.read();
        } catch (EmployeeException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @WebResult(name="findBasedOnId")
    @WebMethod
    public Employee callFilterBasedOnID(@WebParam String employeeId){
        Employee employee = databaseRepositoryImplementation.displayBasedOnEmployeeId(employeeId);
        return employee;
    }

    @WebResult(name="findBasedOnPincode")
    @WebMethod
    public GroupOfEmployee callFilterBasedOnPincode(@WebParam int pincode){
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) databaseRepositoryImplementation.displayBasedOnPinCode(pincode);
            groupOfEmployee.setEmployeeList(employees);
        } catch (EmployeeException e) {
            e.printStackTrace();
        }
        return groupOfEmployee;

    }

    @WebResult(name="findAll")
    @WebMethod
    public GroupOfEmployee callFindAll(){
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) databaseRepositoryImplementation.read();
            groupOfEmployee.setEmployeeList(employees);
        }  catch (EmployeeException e) {
            e.printStackTrace();
        }
        return groupOfEmployee;
    }


}
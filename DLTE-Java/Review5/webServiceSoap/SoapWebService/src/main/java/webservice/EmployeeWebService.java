package webservice;

import backend.pojo.Employee;

import java.util.List;

public class EmployeeWebService {
    private List<Employee> employees ;

    public EmployeeWebService(List<Employee> employees) {
        this.employees = employees;
    }
    public List<Employee> getEmployees(){
        return employees;
    }
    public void setEmployees(List<Employee> employees){
        this.employees=employees;
    }

    public EmployeeWebService() {

    }

    @Override
    public String toString() {
        return "EmployeewebService{" +
                "employees=" + employees +
                '}';
    }
}

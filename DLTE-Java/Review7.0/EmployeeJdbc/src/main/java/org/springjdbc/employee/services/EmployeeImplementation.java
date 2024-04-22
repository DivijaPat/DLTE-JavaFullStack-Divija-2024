package org.springjdbc.employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springjdbc.employee.entity.Employee;
import org.springjdbc.employee.entity.EmployeeAddress;
import org.springjdbc.employee.entity.EmployeeDetails;
import org.springjdbc.employee.exceptions.EmployeeException;
import org.springjdbc.employee.remotes.EmployeeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class EmployeeImplementation implements EmployeeRepository {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Employee create(Employee employee) {
         String id = employee.getEmployeeDetails().getEmployeeId();
          int  acknowledgeBasic = jdbcTemplate.update("insert into Employee values(?,?,?,?)",
                    new Object[]{
                            employee.getEmployeeDetails().getEmployeeId(),
                            employee.getEmployeeDetails().getEmployeeName(),
                            employee.getEmployeeDetails().getEmailId(),
                            employee.getEmployeeDetails().getPhoneNumber()
                    });
        int acknowledgeTemporary = jdbcTemplate.update("insert into Address(EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (?,?,?,?,?,?,0)",
                new Object[]{

                            id,
                            employee.getEmployeeTemporaryAddress().getHouseName(),
                            employee.getEmployeeTemporaryAddress().getStreet(),
                            employee.getEmployeeTemporaryAddress().getCity(),
                            employee.getEmployeeTemporaryAddress().getState(),
                            employee.getEmployeeTemporaryAddress().getPin()
                    });

        int acknowledgePermanent = jdbcTemplate.update("insert into Address(EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (?,?,?,?,?,?,1)",
                    new Object[]{
                            id,
                            employee.getEmployeePermanentAddress().getHouseName(),
                            employee.getEmployeePermanentAddress().getStreet(),
                            employee.getEmployeePermanentAddress().getCity(),
                            employee.getEmployeePermanentAddress().getState(),
                            employee.getEmployeePermanentAddress().getPin()
                    });

        if(acknowledgeBasic==0) throw new EmployeeException(resourceBundle.getString("basic.details.insertion.failed"));
        if(acknowledgeTemporary==0) throw new EmployeeException(resourceBundle.getString("temporary.address.insertion.failed"));
        if(acknowledgePermanent==0) throw new EmployeeException(resourceBundle.getString("permanent.address.insertion.failed"));
        return employee;

    }

    @Override
    public Employee displayBasedOnEmployeeId(String employeeID) {
        Employee employee= jdbcTemplate.queryForObject("SELECT e.EmployeeId, e.EmployeeName, e.emailId, e.phoneNumber, temp.HOUSENAME,temp.STREETNAME,temp.CITYNAME,temp.STATENAME,temp.PINCODE , per.HOUSENAME, per.STREETNAME,per.CITYNAME, per.STATENAME,per.PINCODE  FROM employee e INNER JOIN Address temp ON e.EmployeeId = temp.EMPLOYEEID AND temp.ISTEMPORARY = 1 INNER JOIN Address per ON e.EmployeeId = per.EMPLOYEEID AND per.ISTEMPORARY = 0 WHERE e.EMPLOYEEID=?", new Object[]{employeeID},
                new EmployeeRowMapper());
        if(employee==null) throw new EmployeeException(resourceBundle.getString("no.employee"));
        else return employee;
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pinCode) {
        List<Employee> employeeBasicDetails;
        employeeBasicDetails=jdbcTemplate.query("SELECT e.EmployeeId,e.EmployeeName,e.emailId,e.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee e INNER JOIN Address ta ON e.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 INNER JOIN Address pa ON e.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0 WHERE ta.PINCODE=? or pa.PINCODE=?",
                new Object[]{pinCode,pinCode},
                new EmployeeRowMapper()
        );
        if (employeeBasicDetails.size()==0) throw new EmployeeException(resourceBundle.getString("no.details.based.on.pincode"));
        else return employeeBasicDetails;
    }

    @Override
    public List<Employee> read() {
        List<Employee> employee;
        employee=jdbcTemplate.query("SELECT e.EmployeeId,e.EmployeeName,e.emailId,e.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee e INNER JOIN Address ta ON e.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 INNER JOIN Address pa ON e.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0",
                new Object[]{},
                new EmployeeRowMapper()
        );
         if(employee.size()==0) throw new EmployeeException(resourceBundle.getString("no.employeefound"));
        return employee;
    }


    public class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee=new Employee();
            EmployeeDetails employeeBasicDetails=new EmployeeDetails();
            EmployeeAddress temporaryAddress=new EmployeeAddress();
            EmployeeAddress permanentAddress=new EmployeeAddress();
            employeeBasicDetails.setEmployeeId(rs.getString(1));
            employeeBasicDetails.setEmployeeName(rs.getString(2));
            employeeBasicDetails.setEmailId(rs.getString(3));
            employeeBasicDetails.setPhoneNumber(rs.getLong(4));

            temporaryAddress.setHouseName(rs.getString(5));
            temporaryAddress.setStreet(rs.getString(6));
            temporaryAddress.setCity(rs.getString(7));
            temporaryAddress.setState(rs.getString(8));
            temporaryAddress.setPin(rs.getInt(9));
            permanentAddress.setHouseName(rs.getString(10));
            permanentAddress.setStreet(rs.getString(11));
            permanentAddress.setCity(rs.getString(12));
            permanentAddress.setState(rs.getString(13));
            permanentAddress.setPin(rs.getInt(14));

            employee.setEmployeeDetails(employeeBasicDetails);
            employee.setEmployeeTemporaryAddress(temporaryAddress);
            employee.setEmployeePermanentAddress(permanentAddress);

            return employee;
        }
    }

}
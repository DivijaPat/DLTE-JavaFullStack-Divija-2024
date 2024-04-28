//package org.springjdbc.employee;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springjdbc.employee.entity.Employee;
//import org.springjdbc.employee.entity.EmployeeAddress;
//import org.springjdbc.employee.entity.EmployeeDetails;
//
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.any;
//import org.springjdbc.employee.services.EmployeeImplementation;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class EmployeeApplicationTests {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private EmployeeImplementation repository;
//
//    private Employee employee;
//    private EmployeeAddress temporaryAddress;
//    private EmployeeAddress permanentAddress;
//    private EmployeeDetails details;
//
//    @BeforeEach
//    public void setUp() {
//        details = new EmployeeDetails("444", "Divija", "divija@gmail.com", 9765647656L);
//        temporaryAddress = new EmployeeAddress("Khushi", "mundaje", "ujire", "karnataka", 574371);
//        permanentAddress = new EmployeeAddress("Khushi", "BEML", "global", "karnataka", 776454);
//        employee = new Employee(details, permanentAddress, temporaryAddress);
//    }
//
//
//    @Test
//    void displayBasedOnPinCodeFound() {
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(EmployeeImplementation.EmployeeRowMapper.class)))
//                .thenReturn(Collections.singletonList(employee));
//        List<Employee> employees = repository.displayBasedOnPinCode(776454);
//        assertEquals(1, employees.size());
//        assertNull(employees.size());
//        assertEquals("454",employee.getEmployeeDetails().getEmployeeId());
//    }
//
//}
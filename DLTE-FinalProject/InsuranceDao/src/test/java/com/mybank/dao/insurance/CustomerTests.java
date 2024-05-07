package com.mybank.dao.insurance;

import com.mybank.dao.insurance.entity.Customer;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerTests {
    @InjectMocks
    MyBankUsersServices myBankUsersServices;
    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void testLoadUserByUsername_UserExists() {
        String username = "Divija";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword("div123@");
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.singletonList(customer));
        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }
    @Test
    void testLoadUserByUsername_UserNotExists() {
        String username = "Anu";
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenThrow(UsernameNotFoundException.class);
        // When & Then
        assertThrows(UsernameNotFoundException.class, () -> {
            myBankUsersServices.loadUserByUsername(username);
        });
    }

    @Test
    void testFindByUserName() {
        // Mock customer data
        Customer customer = new Customer();
        customer.setCustomerId(101);
        customer.setCustomerName("meghana");
        customer.setCustomerAddress("bangalore");
        customer.setCustomerContact(8767876622L);
        customer.setCustomerStatus("active");
        customer.setUsername("meghana");
        customer.setPassword("123ert");
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Collections.singletonList(customer));
        Customer result = myBankUsersServices.findByUsernameStream("meghana");
        assertEquals(customer, result);
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
    }

    @Test
    void testFindByUserNameNotFound() {
        // return an empty list of customers
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Collections.emptyList());
        assertThrows(UsernameNotFoundException.class, () -> myBankUsersServices.findByUsernameStream("sunidhi"));
    }

    @Test
    void testSetterMethods() {
        // Create a customer object
        Customer customer = new Customer();
        // Set values using setter methods
        customer.setCustomerId(123);
        customer.setCustomerName("John Doe");
        customer.setCustomerAddress("123 Main St");
        customer.setCustomerStatus("open");
        customer.setCustomerContact(9876543210L);
        customer.setUsername("johndoe");
        customer.setPassword("password");
        assertEquals(123, customer.getCustomerId());
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("123 Main St", customer.getCustomerAddress());
        assertEquals("open", customer.getCustomerStatus());
        assertEquals(9876543210L, customer.getCustomerContact());
        assertEquals("johndoe", customer.getUsername());
        assertEquals("password", customer.getPassword());
    }

    @Test
    void testGetterMethods() {
        // Create a customer object
        Customer customer = new Customer();
        // Set values manually
        customer.setCustomerId(123);
        customer.setCustomerName("Diya");
        customer.setCustomerAddress("Mangalore Main Street");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9876543210L);
        customer.setUsername("diya");
        customer.setPassword("diya21345");
        // Verify that getter methods return the correct values
        assertEquals(123, customer.getCustomerId());
        assertEquals("Diya", customer.getCustomerName());
        assertEquals("Mangalore Main Street", customer.getCustomerAddress());
        assertEquals("active", customer.getCustomerStatus());
        assertEquals(9876543210L, customer.getCustomerContact());
        assertEquals("diya", customer.getUsername());
        assertEquals("diya21345", customer.getPassword());
    }

    @Test
    void signingUp_InvalidCustomerData() {
        Customer invalidCustomer = new Customer();
        invalidCustomer.setCustomerName(null); // Missing required field
        assertDoesNotThrow(() -> myBankUsersServices.signUp(invalidCustomer));
    }

    @Test
    public void testGetAccountNumberByCustomerId() {
        int customerId = 123;
        List<Integer> expectedAccountNumbers = Arrays.asList(1001, 1002, 1003);
        when(jdbcTemplate.queryForList(anyString(), any(Object[].class), eq(Integer.class)))
                .thenReturn(expectedAccountNumbers);
        List<Integer> actualAccountNumbers = myBankUsersServices.getAccountNumberByCustomerId(customerId);
        verify(jdbcTemplate).queryForList(anyString(), any(Object[].class), eq(Integer.class));
        assertEquals(expectedAccountNumbers, actualAccountNumbers);
    }
    @Test
    public void testSignUp() {
        Customer myBankUsers = new Customer();
        myBankUsers.setCustomerName("John Doe");
        myBankUsers.setCustomerAddress("123 Main St");
        myBankUsers.setCustomerStatus("Active");
        myBankUsers.setCustomerContact(1234567890L);
        myBankUsers.setUsername("johndoe");
        myBankUsers.setPassword("psword12");
        myBankUsers.setAttempts(1);
        myBankUsersServices.signUp(myBankUsers);
        verify(jdbcTemplate).update("insert into  MYBANK_APP_CUSTOMER values(CUSTOMERID_SEQ.nextval,?,?,?,?,?,?,?)",
                "John Doe", "123 Main St", "Active", 1234567890L, "johndoe", "psword12", 1);
    }
    @Test
    public void testUpdateAttempts() {
        Customer myBankUsers = new Customer();
        myBankUsers.setUsername("johndoe");
        myBankUsers.setAttempts(3);
        myBankUsersServices.updateAttempts(myBankUsers);
        verify(jdbcTemplate).update("update MYBANK_APP_CUSTOMER set attempts=? where username=?", 3, "johndoe");
    }

    @Test
    public void testUpdateStatus() {
        Customer myBankUsers = new Customer();
        myBankUsers.setUsername("johndoe");
        myBankUsersServices.updateStatus(myBankUsers);
        verify(jdbcTemplate).update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?", "johndoe");
    }

    @Test
    public void testGetCustomerName() {
        String username = "johndoe";
        String expectedName = "John Doe";
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenReturn(expectedName);
        String actualName = myBankUsersServices.getCustomerName(username);
        verify(jdbcTemplate).queryForObject("SELECT c.CUSTOMER_NAME FROM mybank_app_customer c WHERE c.username =  ?",
                new Object[]{username}, String.class);
        assertEquals(expectedName, actualName);
    }
    }


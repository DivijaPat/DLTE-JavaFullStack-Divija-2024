package com.mybank.dao.insurance;

import com.mybank.dao.insurance.entity.Customer;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import com.mybank.dao.insurance.services.InsuranceServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class TestInsuranceByCoverage {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    InsuranceRepository insuranceRepository;
    @Mock
    private CallableStatement callableStatement = mock(CallableStatement.class);
    @Mock
    private ResultSet resultSet = mock(ResultSet.class);

    @InjectMocks
    private InsuranceServices services;


    @Test
    public void testFindAllByCoverage() throws SQLException {
        Double startLimit = 20000D;
        Double endLimit = 40000D;
        int id=101;
        InsuranceAvailed insuranceAvailed1 = new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10);
        InsuranceAvailed insuranceAvailed2 = new InsuranceAvailed(2, 2, 2, 30000D, 1400D, "health", "reliance", "accidental death", 5);

        List<InsuranceAvailed> expectedList = Stream.of(insuranceAvailed1, insuranceAvailed2).collect(Collectors.toList());

        when(insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit)).thenReturn(expectedList);

        // Call the method under test
        List<InsuranceAvailed> actualList = insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit);

        assertNotNull(actualList, "The returned list should not be null");
        assertEquals(expectedList.size(), actualList.size(), "The sizes of the expected and actual lists should match");
        assertEquals(expectedList.get(0).getInsuranceAvailedId(), actualList.get(0).getInsuranceAvailedId(), "The IDs of the first elements should match");
    }
    @Test
    public void testFindAllByCoverageFailure() throws SQLException {
        Double startLimit = 20000D;
        Double endLimit = 40000D;
        int id=102;
        InsuranceAvailed insuranceAvailed1 = new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10);
        InsuranceAvailed insuranceAvailed2 = new InsuranceAvailed(2, 2, 2, 30000D, 1400D, "health", "reliance", "accidental death", 5);

        List<InsuranceAvailed> expectedList = Stream.of(insuranceAvailed1, insuranceAvailed2).collect(Collectors.toList());

        when(insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit)).thenReturn(expectedList);

        // Call the method under test
        List<InsuranceAvailed> actualList = insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit);

        assertNotNull(actualList);
        verify(insuranceRepository, times(1)).findByInsuranceCoverage(id,startLimit, endLimit);
    }

    @Test
    void testInsuranceNotFound() {
        int id = 1;
        Double minLimit = 24500D;
        Double maxLimit = 56000D;
        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(id), eq(minLimit), eq(maxLimit));

        SQLException exception = assertThrows(SQLException.class, () -> {
            services.findByInsuranceCoverage(id, minLimit, maxLimit);
        });
        assertNotEquals("No Insurance found", exception.getMessage());
    }
    @Test
    public void testPasswordMatch() {
        MyBankUsersServices myBankUsersServices = mock(MyBankUsersServices.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Setup test data
        String username = "testUser";
        String rawPassword = "password123";
        String encodedPassword =passwordEncoder.encode(rawPassword);
        // Configure mock behavior
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))
                .thenReturn(customer);
        // Invoke the authentication process
        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);
        String enteredPassword="password123";
        // Verify the result
        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));

    }

    }



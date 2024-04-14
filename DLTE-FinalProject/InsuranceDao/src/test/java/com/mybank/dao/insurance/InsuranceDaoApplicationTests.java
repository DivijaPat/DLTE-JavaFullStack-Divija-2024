package com.mybank.dao.insurance;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.services.InsuranceServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class InsuranceDaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    InsuranceRepository insuranceRepository;
    @Mock
    private DataSource dataSource = mock(DataSource.class);
    @Mock
    private Connection connection = mock(Connection.class);
    @Mock
    private CallableStatement callableStatement = mock(CallableStatement.class);
    @Mock
    private ResultSet resultSet = mock(ResultSet.class);

    @InjectMocks
    private InsuranceServices services;

    @Test
    void testAllAvailableInsurance() throws SQLSyntaxErrorException, NoDataFoundException {
        // Mocking the response from the database
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailable(1, "life", "jeevan", "KeyBenefits1", 10));
        mockInsuranceList.add(new InsuranceAvailable(2, "vehicle", "sathi", "KeyBenefits2", 20));
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(mockInsuranceList);

        // Calling the method under test
        List<InsuranceAvailable> result = services.allAvailableInsurance();

        // Verifying the result
        assertEquals(2, result.size());    //passes
        assertEquals("jeevan", result.get(1).getInsuranceName()); //passes
    }

    @Test
    void testAllAvailableInsuranceFail() throws SQLSyntaxErrorException, NoDataFoundException {
        // Mocking the response from the database
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailable(1, "life", "jeevan", "KeyBenefits1", 10));
        mockInsuranceList.add(new InsuranceAvailable(2, "medical", "heart", "KeyBenefits2", 20));
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(mockInsuranceList);

        // Calling the method under test
        List<InsuranceAvailable> result = services.allAvailableInsurance();

        // Verifying the result
        assertEquals("home", result.get(0).getInsuranceType()); //fails

    }

    @Test
    void callAllInsuranceAvailable_NoDataFound() {
        // Mocking an empty response from the database
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(new ArrayList<>());

        // Calling the method under test and expecting an exception
        assertThrows(InsuranceAvailableException.class, () -> services.allAvailableInsurance());
    }


    //Testing searchByCoverage



    @Test
    void testFindByInsuranceCoverage() throws SQLException {
        // Setting up mocks for the database interaction
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareCall("{ call fetch_insurance_data(?, ?, ?) }")).thenReturn(callableStatement);
        when(callableStatement.getObject(3)).thenReturn(resultSet);



        // Simulating ResultSet data
        when(resultSet.next()).thenReturn(true, true, false); // Simulate two rows in the result set
        when(resultSet.getInt("INSURANCE_AVAIL_ID")).thenReturn(1, 2);
        when(resultSet.getInt("INSURANCE_ID")).thenReturn(101, 102);
        when(resultSet.getInt("CUSTOMER_ID")).thenReturn(201, 202);
        when(resultSet.getInt("INSURANCE_COVERAGE")).thenReturn(20000, 30000);
        when(resultSet.getDouble("INSURANCE_PREMIUM")).thenReturn(1200.0, 1500.0);
        when(resultSet.getString("INSURANCE_TYPE")).thenReturn("life", "auto");
        when(resultSet.getString("INSURANCE_NAME")).thenReturn("jeevan", "car insurance");
        when(resultSet.getString("INSURANCE_KEY_BENEFITS")).thenReturn("lifetime risk cover", "accident cover");
        when(resultSet.getInt("INSURANCE_LIFETIME")).thenReturn(10, 5);

        // Execute the method under test
        List<InsuranceAvailed> results = services.findByInsuranceCoverage(70000, 80000);

        // Assertions
        assertNotNull(results);
        assertEquals(2, results.size());

        // Assertions for the first retrieved object
        InsuranceAvailed firstRetrieved = results.get(0);
        assertEquals(1, firstRetrieved.getInsuranceAvailedId());
        assertEquals("life", firstRetrieved.getInsuranceType());
        assertEquals("jeevan", firstRetrieved.getInsuranceName());
        assertEquals("lifetime risk cover", firstRetrieved.getInsuranceKeyBenefits());
        assertEquals(10, firstRetrieved.getInsuranceLifetime());

        // Verify interactions
        verify(connection).prepareCall("{ call fetch_insurance_data(?, ?, ?) }");
        verify(callableStatement).setDouble(1, 70000);
        verify(callableStatement).setDouble(2, 80000);
        verify(callableStatement).registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        verify(callableStatement).execute();


    }


//    @Test
//    public void testFindAllByCoverage() throws SQLSyntaxErrorException, NoDataFoundException {
//        Double limit1 = 20000D;
//        Double limit2 = 40000D;
//        InsuranceAvailed insuranceAvailed1 = new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10);
//        InsuranceAvailed insuranceAvailed2 = new InsuranceAvailed(2, 2, 2, 30000D, 1400D, "health", "reliance", "accidental death", 5);
//        InsuranceAvailed insuranceAvailed3 = new InsuranceAvailed(3, 3, 3, 50000D, 1400D, "vehicle", "Bajaj", "cashless claim", 3);
//
//        List<InsuranceAvailed> expectedList1 = Stream.of(insuranceAvailed1, insuranceAvailed2).collect(Collectors.toList());
//        List<InsuranceAvailed> expectedList2 = Stream.of(insuranceAvailed2, insuranceAvailed3).collect(Collectors.toList());
//
//        when(insuranceRepository.findByInsuranceCoverage(limit1, limit2)).thenReturn(expectedList1);
//
//        List<InsuranceAvailed> actualList = services.findByInsuranceCoverage(limit1, 2);
//
//        assertNull(actualList);
//        assertEquals(expectedList1.get(0).getInsuranceAvailedId(), actualList.get(0).getInsuranceAvailedId());
//    }
}


















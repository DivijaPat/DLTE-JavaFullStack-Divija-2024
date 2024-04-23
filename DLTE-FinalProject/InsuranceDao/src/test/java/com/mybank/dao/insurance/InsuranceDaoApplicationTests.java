//package com.mybank.dao.insurance;
//
//import com.mybank.dao.insurance.entity.InsuranceAvailable;
//import com.mybank.dao.insurance.entity.InsuranceAvailed;
//import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
//import com.mybank.dao.insurance.exceptions.NoDataFoundException;
//import com.mybank.dao.insurance.remotes.InsuranceRepository;
//import com.mybank.dao.insurance.services.InsuranceServices;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class InsuranceDaoApplicationTests {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @Mock
//    InsuranceRepository insuranceRepository;
//    @Mock
//    private CallableStatement callableStatement = mock(CallableStatement.class);
//    @Mock
//    private ResultSet resultSet = mock(ResultSet.class);
//
//    @InjectMocks
//    private InsuranceServices services;
//
//    @Test
//    void testAllAvailableInsurance() throws SQLSyntaxErrorException, NoDataFoundException {
//        // Mocking the response from the database
//        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
//        mockInsuranceList.add(new InsuranceAvailable(1, "life", "jeevan", "KeyBenefits1", 10));
//        mockInsuranceList.add(new InsuranceAvailable(2, "vehicle", "sathi", "KeyBenefits2", 20));
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(mockInsuranceList);
//
//        // Calling the method under test
//        List<InsuranceAvailable> result = services.allAvailableInsurance();
//
//        // Verifying the result
//        assertEquals(2, result.size());    //passes
//        assertEquals("jeevan", result.get(0).getInsuranceName()); //passes
//    }
//
//    @Test
//    void testAllAvailableInsuranceFail() throws SQLSyntaxErrorException, NoDataFoundException {
//        // Mocking the response from the database
//        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
//        mockInsuranceList.add(new InsuranceAvailable(1, "life", "jeevan", "KeyBenefits1", 10));
//        mockInsuranceList.add(new InsuranceAvailable(2, "medical", "heart", "KeyBenefits2", 20));
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(mockInsuranceList);
//
//        // Calling the method under test
//        List<InsuranceAvailable> result = services.allAvailableInsurance();
//
//        // Verifying the result
//        assertEquals("home", result.get(0).getInsuranceType()); //fails
//
//    }
//
//    @Test
//    void callAllInsuranceAvailable_NoDataFound() {
//        // Mocking an empty response from the database
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(new ArrayList<>());
//
//        // Calling the method under test and expecting an exception
//        assertThrows(InsuranceAvailableException.class, () -> services.allAvailableInsurance());
//    }
//    @Test
//    public void testFindAllByCoverage() throws SQLException, NoDataFoundException {
//        int customerId=1;
//        Double startLimit = 20000D;
//        Double endLimit = 40000D;
//
//        InsuranceAvailed insuranceAvailed1 = new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10);
//        InsuranceAvailed insuranceAvailed2 = new InsuranceAvailed(2, 2, 2, 30000D, 1400D, "health", "reliance", "accidental death", 5);
//
//        List<InsuranceAvailed> expectedList = Stream.of(insuranceAvailed1, insuranceAvailed2).collect(Collectors.toList());
//
//        when(insuranceRepository.findByInsuranceCoverage(customerId,startLimit, endLimit)).thenReturn(expectedList);
//
//        // Call the method under test
//        List<InsuranceAvailed> actualList = insuranceRepository.findByInsuranceCoverage(customerId,startLimit, endLimit);
//
//        assertNotNull(actualList, "The returned list should not be null"); //Pass
//        assertEquals(expectedList.size(), actualList.size(), "The sizes of the expected and actual lists should match"); //Pass
//        assertEquals(expectedList.get(0).getInsuranceAvailedId(), actualList.get(0).getInsuranceAvailedId(), "The IDs of the first elements should match"); //Pass
//    }
//    @Test
//    public void testFindAllByCoverageFailure() throws SQLException, NoDataFoundException {
//        int customerId=1;
//        Double startLimit = 20000D;
//        Double endLimit = 40000D;
//
//        InsuranceAvailed insuranceAvailed1 = new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10);
//        InsuranceAvailed insuranceAvailed2 = new InsuranceAvailed(2, 2, 2, 30000D, 1400D, "health", "reliance", "accidental death", 5);
//
//        List<InsuranceAvailed> expectedList = Stream.of(insuranceAvailed1, insuranceAvailed2).collect(Collectors.toList());
//
//        when(insuranceRepository.findByInsuranceCoverage(customerId,startLimit, endLimit)).thenReturn(expectedList);
//
//        // Call the method under test
//        List<InsuranceAvailed> actualList = insuranceRepository.findByInsuranceCoverage(customerId,startLimit, endLimit);
//
//        assertNull(actualList);    //Fail
//        verify(insuranceRepository, times(2)).findByInsuranceCoverage(customerId,startLimit, endLimit); //Fail
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

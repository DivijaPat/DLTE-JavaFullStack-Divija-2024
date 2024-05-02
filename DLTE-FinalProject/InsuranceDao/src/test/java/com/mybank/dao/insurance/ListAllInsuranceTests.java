//package com.mybank.dao.insurance;
//
//import com.mybank.dao.insurance.entity.InsuranceAvailable;
//import com.mybank.dao.insurance.exceptions.NoDataFoundException;
//import com.mybank.dao.insurance.services.InsuranceServices;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class ListAllInsuranceTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    InsuranceServices insuranceServices;
//
//    @Test
//    void testAllInsurance() throws SQLSyntaxErrorException, NoDataFoundException {
//        List<InsuranceAvailable> insuranceAvailable = new ArrayList<>();
//        insuranceAvailable.add(new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10));
//        insuranceAvailable.add(new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5));
//        insuranceAvailable.add(new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1));
//        insuranceAvailable.add(new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3));
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(insuranceAvailable);
//        List<InsuranceAvailable> actualList = insuranceServices.allAvailableInsurance();
//        assertNotNull(actualList);
//        assertEquals("life", actualList.get(0).getInsuranceType());
//        assertEquals(3, actualList.get(2).getInsuranceId());
//        assertEquals(4, actualList.size());
//    }
//
//    @Test
//    void testAllInsuranceFailure() throws SQLSyntaxErrorException, NoDataFoundException {
//        List<InsuranceAvailable> insuranceAvailable = new ArrayList<>();
//        insuranceAvailable.add(new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10));
//        insuranceAvailable.add(new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5));
//        insuranceAvailable.add(new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1));
//        insuranceAvailable.add(new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3));
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(insuranceAvailable);
//        List<InsuranceAvailable> actualList = insuranceServices.allAvailableInsurance();
//        assertNotEquals("bajaj", actualList.get(3).getInsuranceName());
//        assertNotEquals("Accidental death cover", actualList.get(0).getInsuranceType());
//        assertNotEquals(2, actualList.size());
//        assertNotSame("travel",actualList.get(1).getInsuranceType());
//    }
//    @Test
//    void testAllInsuranceException() {
//        // Mocking an empty response from the database
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(new ArrayList<>());
//        // Calling the method under test and expecting an exception
//        assertThrows(NoDataFoundException.class, () -> insuranceServices.allAvailableInsurance());
//    }
//    @Test
//    void testAllInsuranceExceptionFailure(){
//        List<InsuranceAvailable> insuranceAvailable = new ArrayList<>();
//        insuranceAvailable.add(new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10));
//        insuranceAvailable.add(new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5));
//        insuranceAvailable.add(new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1));
//        insuranceAvailable.add(new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3));
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(insuranceAvailable);
//        assertDoesNotThrow(() -> insuranceServices.allAvailableInsurance());
//
//    }
//
//}
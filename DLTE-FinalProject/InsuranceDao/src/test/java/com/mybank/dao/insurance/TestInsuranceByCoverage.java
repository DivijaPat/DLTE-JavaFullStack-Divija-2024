//package com.mybank.dao.insurance;
//
//
//import com.mybank.dao.insurance.entity.InsuranceAvailed;
//import com.mybank.dao.insurance.exceptions.NoDataFoundException;
//import com.mybank.dao.insurance.remotes.InsuranceRepository;
//import com.mybank.dao.insurance.services.InsuranceServices;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.CallableStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//
//@SpringBootTest
//public class TestInsuranceByCoverage {
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
//
//    @Test
//    public void testFindAllByCoverage() throws SQLException {
//        Double startLimit = 20000D;
//        Double endLimit = 40000D;
//        int id=101;
//        InsuranceAvailed insuranceAvailed1 = new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10);
//        InsuranceAvailed insuranceAvailed2 = new InsuranceAvailed(2, 2, 2, 30000D, 1400D, "health", "reliance", "accidental death", 5);
//
//        List<InsuranceAvailed> expectedList = Stream.of(insuranceAvailed1, insuranceAvailed2).collect(Collectors.toList());
//
//        when(insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit)).thenReturn(expectedList);
//
//        // Call the method under test
//        List<InsuranceAvailed> actualList = insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit);
//
//        assertNotNull(actualList, "The returned list should not be null"); //Pass
//        assertEquals(expectedList.size(), actualList.size(), "The sizes of the expected and actual lists should match"); //Pass
//        assertEquals(expectedList.get(0).getInsuranceAvailedId(), actualList.get(0).getInsuranceAvailedId(), "The IDs of the first elements should match"); //Pass
//    }
//    @Test
//    public void testFindAllByCoverageFailure() throws SQLException {
//        Double startLimit = 20000D;
//        Double endLimit = 40000D;
//        int id=102;
//        InsuranceAvailed insuranceAvailed1 = new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10);
//        InsuranceAvailed insuranceAvailed2 = new InsuranceAvailed(2, 2, 2, 30000D, 1400D, "health", "reliance", "accidental death", 5);
//
//        List<InsuranceAvailed> expectedList = Stream.of(insuranceAvailed1, insuranceAvailed2).collect(Collectors.toList());
//
//        when(insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit)).thenReturn(expectedList);
//
//        // Call the method under test
//        List<InsuranceAvailed> actualList = insuranceRepository.findByInsuranceCoverage(id,startLimit, endLimit);
//
//        assertNotNull(actualList);    //Fail
//        verify(insuranceRepository, times(1)).findByInsuranceCoverage(id,startLimit, endLimit); //Fail
//    }
//}
//

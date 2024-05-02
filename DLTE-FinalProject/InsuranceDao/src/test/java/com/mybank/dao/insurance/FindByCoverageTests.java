//package com.mybank.dao.insurance;
//
//import com.mybank.dao.insurance.entity.InsuranceAvailed;
//import com.mybank.dao.insurance.services.InsuranceServices;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class FindByCoverageTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    InsuranceServices insuranceServices;
//
//
//    @Test
//    public void testFindAllByCoverage() throws SQLException {
//        Double minCoverage = 20000D;
//        Double maxCoverage = 40000D;
//        int id=101;
//        List<InsuranceAvailed> insuranceAvailed=new ArrayList<>();
//        insuranceAvailed.add( new InsuranceAvailed(1, 1, 1, 20500D, 1400D, "life", "LIC", "lifetime risk coverage", 10));
//        insuranceAvailed.add(new InsuranceAvailed(2, 2, 2, 60000D, 1400D, "health", "reliance", "accidental death", 5));
////
////        List<InsuranceAvailed> expectedList = Stream.of(insuranceAvailed).collect(Collectors.toList());
////        expectedList.forEach(System.out::println);        // Call the method under test
//        when(insuranceServices.findByInsuranceCoverage(id,minCoverage, maxCoverage)).thenReturn(insuranceAvailed);
//        List<InsuranceAvailed> actualList = insuranceServices.findByInsuranceCoverage(id,minCoverage, maxCoverage);
//
////          assertNotNull(actualList, "The returned list should not be null"); //Pass
////        assertEquals(insuranceAvailed.size(), actualList.size(), "The sizes of the expected and actual lists should match"); //Pass
////        assertEquals(expectedList.get(0).getInsuranceAvailedId(), actualList.get(0).getInsuranceAvailedId(), "The IDs of the first elements should match"); //Pass
//    }
//}
//

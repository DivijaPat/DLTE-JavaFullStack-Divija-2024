package com.mybank.dao.insurance;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.services.InsuranceServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig
public class ListAllInsuranceTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    InsuranceServices insuranceServices;

    @Test
    void testAllInsurance() throws SQLSyntaxErrorException {
        List<InsuranceAvailable> insuranceAvailable = new ArrayList<>();
        insuranceAvailable.add(new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10));
        insuranceAvailable.add(new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5));
        insuranceAvailable.add(new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1));
        insuranceAvailable.add(new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3));
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(insuranceAvailable);
        List<InsuranceAvailable> actualList = insuranceServices.allAvailableInsurance();
        assertNotNull(actualList);
        assertEquals("life", actualList.get(0).getInsuranceType());
        assertEquals(3, actualList.get(2).getInsuranceId());
        assertEquals(4, actualList.size());
    }

    @Test
    void testAllInsuranceFailure() throws SQLSyntaxErrorException {
        List<InsuranceAvailable> insuranceAvailable = new ArrayList<>();
        insuranceAvailable.add(new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10));
        insuranceAvailable.add(new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5));
        insuranceAvailable.add(new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1));
        insuranceAvailable.add(new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3));
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(insuranceAvailable);
        List<InsuranceAvailable> actualList = insuranceServices.allAvailableInsurance();
        assertNotEquals("bajaj", actualList.get(3).getInsuranceName());
        assertNotEquals("Accidental death cover", actualList.get(0).getInsuranceType());
        assertNotEquals(2, actualList.size());
        assertNotSame("travel",actualList.get(1).getInsuranceType());
    }

    @Test
    void testAllInsuranceExceptionFailure(){
        List<InsuranceAvailable> insuranceAvailable = new ArrayList<>();
        insuranceAvailable.add(new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10));
        insuranceAvailable.add(new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5));
        insuranceAvailable.add(new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1));
        insuranceAvailable.add(new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3));
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(insuranceAvailable);
        assertDoesNotThrow(() -> insuranceServices.allAvailableInsurance());
    }
    @Test
    void testAllInsuranceExceptionFail() {
        // Mocking an empty response from the database
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(new ArrayList<>());
        assertThrows(SQLException.class, () -> insuranceServices.findByInsuranceCoverage(1,20000,40000));
    }

    @Test
    void callAllInsuranceAvailable_DataAccessException() throws SQLException {
        when(jdbcTemplate.query(any(String.class), any(InsuranceServices.CardMapper.class)))
            .thenThrow(new DataAccessException("Test DataAccessException") {});
        assertThrows(SQLException.class, () -> insuranceServices.allAvailableInsurance());
}
    @Test
    public void testGettersAndSetters() {
        InsuranceAvailable insuranceAvailable = new InsuranceAvailable();
        insuranceAvailable.setInsuranceId(123);
        insuranceAvailable.setInsuranceType("life");
        insuranceAvailable.setInsuranceName("jeevan");
        insuranceAvailable.setInsuranceKeyBenefits("death cover");
        insuranceAvailable.setInsuranceLifetime(5);
        assertEquals(123, insuranceAvailable.getInsuranceId());
        assertEquals("life", insuranceAvailable.getInsuranceType());
        assertEquals("jeevan", insuranceAvailable.getInsuranceName());
        assertEquals("death cover", insuranceAvailable.getInsuranceKeyBenefits());
        assertEquals(5, insuranceAvailable.getInsuranceLifetime());
    }
}



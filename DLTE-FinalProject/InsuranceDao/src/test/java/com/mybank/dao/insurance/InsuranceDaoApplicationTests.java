package com.mybank.dao.insurance;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.services.InsuranceServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class InsuranceDaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private InsuranceServices services;

    @Test
    void testAllAvailableInsurance() throws SQLSyntaxErrorException {
        // Mocking the response from the database
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailable(1, "Type1", "Name1", "KeyBenefits1", 10));
        mockInsuranceList.add(new InsuranceAvailable(2, "Type2", "Name2", "KeyBenefits2", 20));
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(mockInsuranceList);

        // Calling the method under test
        List<InsuranceAvailable> result = services.allAvailableInsurance();

        // Verifying the result
        assertEquals(2, result.size());    //passes
        assertEquals("Name2", result.get(1).getInsuranceName()); //passes
    }

   // @Test
    void testAllAvailableInsuranceFail() throws SQLSyntaxErrorException {
        // Mocking the response from the database
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailable(1, "Type1", "Name1", "KeyBenefits1", 10));
        mockInsuranceList.add(new InsuranceAvailable(2, "Type2", "Name2", "KeyBenefits2", 20));
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(mockInsuranceList);

        // Calling the method under test
        List<InsuranceAvailable> result = services.allAvailableInsurance();

        // Verifying the result
        assertEquals("Type", result.get(0).getInsuranceType()); //fails

    }

    @Test
    void callAllInsuranceAvailable_NoDataFound() {
        // Mocking an empty response from the database
        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(new ArrayList<>());

        // Calling the method under test and expecting an exception
        assertThrows(InsuranceAvailableException.class, () -> services.allAvailableInsurance());
    }

}
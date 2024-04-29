package mybank.insurance.webservice;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.services.InsuranceServices;

import mybank.insurance.webservice.rest.controller.InsuranceController;
import org.apache.catalina.security.SecurityConfig;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SearchByInsuranceCoverageTests {

    private MockMvc mockMvc;

    @Mock
    private InsuranceRepository insuranceRepository;

    @InjectMocks
    private InsuranceController insuranceController;


    @BeforeEach
    public void setUp() throws SQLException, NoDataFoundException {
        mockMvc = MockMvcBuilders.standaloneSetup(insuranceController).build();
        // Setup mocks
        List<InsuranceAvailed> mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailed());

        when(insuranceRepository.findByInsuranceCoverage(101, 1000.0,2000.0))
                .thenReturn(mockInsuranceList);
        when(insuranceRepository.findByInsuranceCoverage(101,3000.0, 4000.0))
                .thenReturn(new ArrayList<>());
    }

//    @Test
    public void testFindByInsuranceCoverageReturnsData() throws Exception {
        mockMvc.perform(get("/module/insurance/{startLimit}/{endLimit}", 1000.0, 2000.0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists()); // Verify that the first element in the response exists
    }

//    @Test
    public void testSearch() throws Exception {
        double start = 1000;
        double end=2000;

        // Act & Assert
        mockMvc.perform(get("http://localhost:8082/module/insurance/{start}/{end}", start,end))
                .andExpect(status().isOk());
    }

//    @Test
    public void testFindByInsuranceCoverageSuccess() throws Exception, NoDataFoundException {
        List<InsuranceAvailed> mockInsuranceList = Arrays.asList(new InsuranceAvailed());

        mockMvc.perform(MockMvcRequestBuilders.get("/module/insurance/{startLimit}/{endLimit}", 1000.0, 5000.0))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

}


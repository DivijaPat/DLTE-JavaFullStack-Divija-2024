package mybank.insurance.webservice;


import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import mybank.insurance.webservice.rest.controller.InsuranceController;
import mybank.insurance.webservice.security.MyBankApi;
import mybank.insurance.webservice.security.OfficialsSuccessHandler;
import mybank.insurance.webservice.soap.endpoint.InsuranceAvailableEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import services.insurance.CallAllInsuranceAvailableRequest;
import services.insurance.CallAllInsuranceAvailableResponse;


import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest


public class EndPointTests {
    @Mock
    private InsuranceRepository insuranceRepository;
    @InjectMocks
    InsuranceAvailableEndpoint insuranceEndpoint;
    @Mock
    JdbcTemplate jdbcTemplate;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private InsuranceController insuranceController;
    @InjectMocks
    private OfficialsSuccessHandler successHandler;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private MyBankApi myBankApi;
    @Mock
    private MyBankUsersServices services;

    //Testing Soap service

    @Test
    public void ListAll() throws SQLSyntaxErrorException, NoDataFoundException {
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        InsuranceAvailable insurance1 = new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        InsuranceAvailable insurance2 = new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        InsuranceAvailable insurance3 = new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        InsuranceAvailable insurance4 = new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
        mockInsuranceList = Stream.of(insurance1, insurance2).collect(Collectors.toList());
        when(insuranceRepository.allAvailableInsurance()).thenReturn(mockInsuranceList);
        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(request);
        assertEquals(insurance1.getInsuranceName(), mockInsuranceList.get(0).getInsuranceName());
        assertEquals(mockInsuranceList.size(), response.getInsurance().size());
        assertNotNull(response.getServiceStatus().getStatus());
    }

    @Test
   public void ListAllFail() throws SQLSyntaxErrorException, NoDataFoundException {
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        InsuranceAvailable insurance1 = new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        InsuranceAvailable insurance2 = new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        InsuranceAvailable insurance3 = new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        InsuranceAvailable insurance4 = new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
        mockInsuranceList = Stream.of(insurance1, insurance2,insurance3,insurance4).collect(Collectors.toList());
        when(insuranceRepository.allAvailableInsurance()).thenReturn(mockInsuranceList);
        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(request);
        assertFalse(insurance3.getInsuranceId() == mockInsuranceList.get(3).getInsuranceId());
        assertNotNull(response.getInsurance());
    }

    @Test
    public void ListAllInternalServerError() throws SQLSyntaxErrorException, NoDataFoundException {
        when(insuranceRepository.allAvailableInsurance()).thenThrow(new SQLSyntaxErrorException());
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(new CallAllInsuranceAvailableRequest());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getServiceStatus().getStatus());

    }
    @Test
    public void ListAllInternalServerErrorFails() throws SQLSyntaxErrorException, NoDataFoundException {
        when(insuranceRepository.allAvailableInsurance()).thenThrow(new SQLSyntaxErrorException());
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(new CallAllInsuranceAvailableRequest());
        assertNotEquals(HttpStatus.BAD_REQUEST.value(), response.getServiceStatus().getStatus());
    }

    @Test
    public void testListAllStatus() throws Exception, NoDataFoundException {
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        InsuranceAvailable insurance1 = new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        InsuranceAvailable insurance2 = new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        InsuranceAvailable insurance3 = new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        InsuranceAvailable insurance4 = new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
        mockInsuranceList = Stream.of(insurance1, insurance2,insurance3,insurance4).collect(Collectors.toList());
        when(insuranceRepository.allAvailableInsurance()).thenReturn(mockInsuranceList);
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(new CallAllInsuranceAvailableRequest());
        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
    }
    @Test
    public void testListAllStatusFail() throws Exception, NoDataFoundException {
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        InsuranceAvailable insurance1 = new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        InsuranceAvailable insurance2 = new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        InsuranceAvailable insurance3 = new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        InsuranceAvailable insurance4 = new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
        mockInsuranceList = Stream.of(insurance1, insurance2,insurance3,insurance4).collect(Collectors.toList());
        when(insuranceRepository.allAvailableInsurance()).thenReturn(mockInsuranceList);
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(new CallAllInsuranceAvailableRequest());
        assertNotEquals(HttpStatus.NO_CONTENT.value(), response.getServiceStatus().getStatus());
    }



}


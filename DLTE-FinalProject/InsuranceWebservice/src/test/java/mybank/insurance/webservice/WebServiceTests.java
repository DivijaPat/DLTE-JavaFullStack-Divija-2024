package mybank.insurance.webservice;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import mybank.insurance.webservice.soap.endpoint.InsuranceAvailableEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import services.insurance.CallAllInsuranceAvailableRequest;
import services.insurance.CallAllInsuranceAvailableResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class WebServiceTests {

    @Autowired
    private InsuranceAvailableEndpoint insuranceEndpoint;

    @MockBean
    private InsuranceRepository insuranceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void ListAll() throws SQLSyntaxErrorException, NoDataFoundException {
        List<com.mybank.dao.insurance.entity.InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance1 = new com.mybank.dao.insurance.entity.InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance2 = new com.mybank.dao.insurance.entity.InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance3 = new com.mybank.dao.insurance.entity.InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance4 = new com.mybank.dao.insurance.entity.InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
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
        List<com.mybank.dao.insurance.entity.InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance1 = new com.mybank.dao.insurance.entity.InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance2 = new com.mybank.dao.insurance.entity.InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance3 = new com.mybank.dao.insurance.entity.InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance4 = new com.mybank.dao.insurance.entity.InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
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
        List<com.mybank.dao.insurance.entity.InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance1 = new com.mybank.dao.insurance.entity.InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance2 = new com.mybank.dao.insurance.entity.InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance3 = new com.mybank.dao.insurance.entity.InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance4 = new com.mybank.dao.insurance.entity.InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
        mockInsuranceList = Stream.of(insurance1, insurance2,insurance3,insurance4).collect(Collectors.toList());
        when(insuranceRepository.allAvailableInsurance()).thenReturn(mockInsuranceList);
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(new CallAllInsuranceAvailableRequest());
        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
    }
    @Test
    public void testListAllStatusFail() throws Exception, NoDataFoundException {
        List<com.mybank.dao.insurance.entity.InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance1 = new com.mybank.dao.insurance.entity.InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance2 = new com.mybank.dao.insurance.entity.InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance3 = new com.mybank.dao.insurance.entity.InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1);
        com.mybank.dao.insurance.entity.InsuranceAvailable insurance4 = new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3);
        mockInsuranceList = Stream.of(insurance1, insurance2,insurance3,insurance4).collect(Collectors.toList());
        when(insuranceRepository.allAvailableInsurance()).thenReturn(mockInsuranceList);
        CallAllInsuranceAvailableResponse response = insuranceEndpoint.listInsurance(new CallAllInsuranceAvailableRequest());
        assertNotEquals(HttpStatus.NO_CONTENT.value(), response.getServiceStatus().getStatus());
    }
}
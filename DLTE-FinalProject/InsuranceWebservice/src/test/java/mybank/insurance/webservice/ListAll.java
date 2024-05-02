//package mybank.insurance.webservice;
//
//import com.mybank.dao.insurance.entity.InsuranceAvailable;
//
//import com.mybank.dao.insurance.exceptions.NoDataFoundException;
//import com.mybank.dao.insurance.remotes.InsuranceRepository;
//import mybank.insurance.webservice.soap.endpoint.InsuranceAvailableEndpoint;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import services.insurance.CallAllInsuranceAvailableRequest;
//import services.insurance.CallAllInsuranceAvailableResponse;
//import services.insurance.ServiceStatus;
//
//import javax.servlet.http.HttpServletResponse;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class ListAll {
//    @MockBean
//    private InsuranceRepository repository;
//
//    @InjectMocks
//    private InsuranceAvailableEndpoint endpoint;
//
//    @Test
//    public void testListInsurance_Success() throws SQLSyntaxErrorException, NoDataFoundException {
//        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
//        ServiceStatus expectedServiceStatus = new ServiceStatus();
//        expectedServiceStatus.setStatus(HttpServletResponse.SC_OK);
//        expectedServiceStatus.setMessage("OK");
//
//        List<InsuranceAvailable> insuranceList = Stream.of(
//                new InsuranceAvailable(1, "Type1", "Name1", "KeyBenefits1", 7),
//                new InsuranceAvailable(2, "Type2", "Name2", "KeyBenefits2", 8)
//
//        ).collect((Collectors.toList()));
//        when(repository.allAvailableInsurance()).thenReturn(insuranceList);
//
//        CallAllInsuranceAvailableResponse response = endpoint.listInsurance(request);
//        assertEquals(2, response.getInsurance().size()); //passes
//        assertNotNull(response); //passes
//        assertEquals(expectedServiceStatus.getStatus(), response.getServiceStatus().getStatus()); //passes
//        assertNotEquals(expectedServiceStatus.getMessage(), response.getServiceStatus().getMessage()); //fails
//        assertNotNull(response.getInsurance());  //pass
//        assertNotEquals(1, response.getInsurance().size()); //fail
//    }
//
//    @Test
//    public void testListInsurance_SQLException() throws SQLSyntaxErrorException, NoDataFoundException {
//
//        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
//        ServiceStatus expectedServiceStatus = new ServiceStatus();
//        expectedServiceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        expectedServiceStatus.setMessage("Database error");
//
//        when(repository.allAvailableInsurance()).thenThrow(SQLSyntaxErrorException.class);
//
//        CallAllInsuranceAvailableResponse response = endpoint.listInsurance(request);
//
//        // Assert
//            assertNotNull(response); //pass
//          assertEquals(expectedServiceStatus.getStatus(), response.getServiceStatus().getStatus()); //pass
//        assertEquals(expectedServiceStatus.getMessage(), response.getServiceStatus().getMessage()); //pass
//       assertTrue(response.getInsurance().isEmpty()); //pass
//    }
//}
//
//

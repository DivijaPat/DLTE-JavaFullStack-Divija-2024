//package mybank.insurance.webservice;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mybank.dao.insurance.entity.Customer;
//import com.mybank.dao.insurance.entity.InsuranceAvailable;
//import com.mybank.dao.insurance.entity.InsuranceAvailed;
//import com.mybank.dao.insurance.exceptions.InsuranceAvailedException;
//import com.mybank.dao.insurance.exceptions.NoDataFoundException;
//import com.mybank.dao.insurance.remotes.InsuranceRepository;
//import com.mybank.dao.insurance.security.MyBankUsersServices;
//import com.mybank.dao.insurance.services.InsuranceServices;
//import mybank.insurance.webservice.mvc.ControllerMvc;
//import mybank.insurance.webservice.rest.controller.InsuranceController;
//import mybank.insurance.webservice.security.MyBankApi;
//import mybank.insurance.webservice.security.OfficialsFailureHandler;
//import mybank.insurance.webservice.security.OfficialsSuccessHandler;
//import mybank.insurance.webservice.soap.configs.SoapServiceConfiguration;
//import mybank.insurance.webservice.soap.endpoint.InsuranceAvailableEndpoint;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.HttpStatus;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import org.springframework.ui.Model;
//import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
//import org.springframework.xml.xsd.XsdSchema;
//import services.insurance.CallAllInsuranceAvailableRequest;
//import services.insurance.CallAllInsuranceAvailableResponse;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//
//public class EndPointTests {
//    @Mock
//    private InsuranceRepository insuranceRepository;
//    @Mock
//    private Authentication authentication;
//    @InjectMocks
//    InsuranceAvailableEndpoint insuranceEndpoint;
//    @Mock
//    JdbcTemplate jdbcTemplate;
//    @Autowired
//    private MockMvc mockMvc;
//    @InjectMocks
//    private InsuranceController insuranceController;
//    @InjectMocks
//    private OfficialsSuccessHandler successHandler;
//    @InjectMocks
//    private OfficialsFailureHandler failureHandler;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @InjectMocks
//    private MyBankApi myBankApi;
//    @Mock
//    private MyBankUsersServices services;
//    @InjectMocks
//    private ControllerMvc controllerMvc;
//    @Mock
//    private HttpServletRequest request;
//    @Mock
//    private HttpServletResponse response;
//    @Mock
//    ApplicationContext applicationContext;
//    @Mock
//    private SecurityContext securityContext;
//
//
//    //Testing Soap service
//
//
//  //  MyBankApi
//
//
//    //restController
//
//
//    // mvc -------
//
//
//    // validation-------
//
//    // success and failure handler
//
//
//
//
//    @Test
//    public void testAllInsuranceException() {
//        // Mocking an empty response from the database
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(new ArrayList<>());
//        // Calling the method under test and expecting an exception
//        assertDoesNotThrow(() -> insuranceRepository.allAvailableInsurance());    }
//
//    @Test
//    public void testAllInsuranceExceptionFail() {
//        // Mocking an empty response from the database
//        when(jdbcTemplate.query(anyString(), any(InsuranceServices.CardMapper.class))).thenReturn(new ArrayList<>());
//        // Calling the method under test and expecting an exception
//        assertDoesNotThrow(() -> insuranceRepository.findByInsuranceCoverage(1,20000,40000));
//    }
//
//
//    @Test
//    public void servletRegistrationBean() {
//        SoapServiceConfiguration soapServiceConfiguration = new SoapServiceConfiguration();
//        ServletRegistrationBean registrationBean = soapServiceConfiguration.servletRegistrationBean(applicationContext);
//
//        assertEquals("/insurancerepo/*", registrationBean.getUrlMappings().iterator().next());
//    }
//
//
//
//
//
//    // Helper method to convert object to JSON string
//    private String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    @Test
////    public void onAuthenticationFailure_ValidCustomer() throws IOException, ServletException {
////        MockHttpServletRequest request = new MockHttpServletRequest();
////        MockHttpServletResponse response = new MockHttpServletResponse();
////        String username = "testUser";
////        MyBankUsers customer = new MyBankUsers();
////        customer.setUsername(username);
////        customer.setAttempts(2);
////        when(services.findByUsernameStream(username)).thenReturn(customer);
////        AuthenticationException exception = new LockedException("Invalid credentials");
////        failureHandler.onAuthenticationFailure(request, response, exception);
////        verify(services, times(1)).updateAttempts(any(MyBankUsers.class));
////        verify(services, never()).updateStatus(any(MyBankUsers.class));
////        verify(response).sendRedirect("/web/?error= Invalid credentials");
////    }
//
////    @Test
////    public void onAuthenticationFailure_MaxAttemptsReached() throws IOException, ServletException {
////        MockHttpServletRequest request = new MockHttpServletRequest();
////        MockHttpServletResponse response = new MockHttpServletResponse();
////
////        String username = "testUser";
////        MyBankUsers customer = new MyBankUsers();
////        customer.setUsername(username);
////        customer.setAttempts(3); // Max attempts reached
////
////
////        when(services.findByUsernameStream(username)).thenReturn(customer);
////
////        AuthenticationException exception = new LockedException("Invalid credentials");
////
////        failureHandler.onAuthenticationFailure(request, response, exception);
////
////        verify(services, never()).updateAttempts(any(MyBankUsers.class));
////        verify(services, times(1)).updateStatus(any(MyBankUsers.class));
////        verify(response).sendRedirect("/web/?error=Invalid credentials");
////    }
////
////    @Test
////    public void onAuthenticationFailure_NullCustomer() throws IOException, ServletException {
////        MockHttpServletRequest request = new MockHttpServletRequest();
////        MockHttpServletResponse response = new MockHttpServletResponse();
////
////        String username = "testUser";
////
////        when(services.findByUsernameStream(anyString())).thenThrow(new InsuranceAvailedException("Customer not found")); // Stubbing to handle any string argument
////
////        AuthenticationException exception = new LockedException("Invalid credentials");
////
////        failureHandler.onAuthenticationFailure(request, response, exception);
////
////        verify(services, never()).updateAttempts(any(MyBankUsers.class));
////        verify(services, never()).updateStatus(any(MyBankUsers.class));
////        verify(response).sendRedirect("/ui/?error=Customer not found");
////    }
//
//
//
//
////    @Test
////    public void testOnAuthenticationSuccess_InactiveCustomer() throws Exception {
////        MyBankUsers customer = new MyBankUsers();
////        customer.setCustomerStatus("inactive");
////
////        when(authentication.getPrincipal()).thenReturn(customer);
////
////        successHandler.onAuthenticationSuccess(request, response, authentication);
////
////        verify(response).encodeRedirectURL("/web/?error=Max attempts reached contact admin");
////    }
//
//}
//
//
//

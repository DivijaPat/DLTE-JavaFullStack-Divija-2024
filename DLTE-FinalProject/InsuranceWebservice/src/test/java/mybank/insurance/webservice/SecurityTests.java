package mybank.insurance.webservice;

import com.mybank.dao.insurance.entity.Customer;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import mybank.insurance.webservice.rest.controller.InsuranceController;
import mybank.insurance.webservice.security.MyBankApi;
import mybank.insurance.webservice.security.OfficialsFailureHandler;
import mybank.insurance.webservice.security.OfficialsSuccessHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class SecurityTests {

    @InjectMocks
    private OfficialsSuccessHandler successHandler;
    @Mock
    MyBankUsersServices myBankUsersServices;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    private MyBankApi myBankApi;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private InsuranceController insuranceController;
    @Mock
    Authentication authentication;
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        mockMvc = MockMvcBuilders.standaloneSetup(insuranceController).build();
    }

    @Test
    public void onAuthenticationSuccess_CustomerInactive() throws ServletException, IOException {
        // Mock authentication
        Customer customer = new Customer();
        customer.setCustomerStatus("inactive");
        Authentication authentication = new UsernamePasswordAuthenticationToken(customer, null);
        // Mock request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        // Call the method
        successHandler.onAuthenticationSuccess(request, response, authentication);
    }

    @Test
    public void testPasswordMatch() {
        MyBankUsersServices myBankUsersServices = mock(MyBankUsersServices.class);
        passwordEncoder = new BCryptPasswordEncoder();
        String username = "Akshira";
        String rawPassword = "aksh@123";
        String encodedPassword =passwordEncoder.encode(rawPassword);
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))
                .thenReturn(customer);
        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);
        String enteredPassword="aksh@123";
        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));
    }

    @Test
    public void save() {
        // Mock data
        Customer mockCustomer = new Customer();
        mockCustomer.setUsername("User");
        mockCustomer.setPassword("Pass");
        when(passwordEncoder.encode(mockCustomer.getPassword())).thenReturn("encodedPassword");
        when(myBankUsersServices.signUp(mockCustomer)).thenReturn(mockCustomer);
        Customer savedCustomer = myBankApi.save(mockCustomer);
        verify(passwordEncoder).encode("Pass");
        verify(myBankUsersServices).signUp(mockCustomer);
        assertEquals("User", savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming getPassword() returns the encoded password
    }


    @Test
    public void testGetUser() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("testUser", "testPassword");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        String result = insuranceController.getUser();
        assertEquals("testUser", result);
    }

    @Test
    public void testOnAuthenticationFailure_InactiveStatus() throws Exception {
        // Mock authentication
        Customer myBankOfficials = new Customer();
        myBankOfficials.setCustomerStatus("Inactive");
        Authentication authentication = new UsernamePasswordAuthenticationToken(myBankOfficials, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        successHandler.onAuthenticationSuccess(request, response, authentication);
        // Verify behavior
        assertNotEquals("/web/?errors=Contact%20administrator", response.encodeRedirectURL("/web/?errors=Contact administrator"));
    }
//    @Test
//    public void testGetCustomerName() {
//        // Mock SecurityContextHolder to return a mock Authentication object
//        Authentication authentication = Mockito.mock(Authentication.class);
//        Mockito.when(authentication.getName()).thenReturn("Aru");
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        Mockito.when(myBankUsersServices.getCustomerName(Mockito.anyString())).thenReturn("Aru");
//        String result = insuranceController.getCustomerName();
//        Assertions.assertEquals("Aru", result);
//    }

//    @Test
//    public void testSearchByCoverageSuccess() throws Exception {
//    String requestBody = "{\"startLimit\":20000,\"endLimit\":45000\"}";
//    Authentication authentication = mock(Authentication.class);
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//    when(authentication.getName()).thenReturn("testUser");
//    Customer customer = new Customer();
//    customer.setCustomerId(123);
//    customer.setCustomerName("Divija");
//    customer.setCustomerAddress("Ujire");
//    customer.setCustomerStatus("active");
//    customer.setCustomerContact(8765432345L);
//    customer.setUsername("testUser");
//    customer.setPassword("12233");
//    customer.setAttempts(1);
//    when(myBankUsersServices.findByUsernameStream("testUser")).thenReturn(customer);
//    when(myBankUsersServices.getCustomerName("testUser")).thenReturn(String.valueOf(Collections.singletonList(customer)));
//}

}

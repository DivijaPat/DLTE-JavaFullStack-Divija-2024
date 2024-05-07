package mybank.insurance.webservice;


import com.mybank.dao.insurance.entity.Customer;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import mybank.insurance.webservice.security.OfficialsFailureHandler;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

@SpringBootTest

@AutoConfigureMockMvc

class FailureTest {
    @Mock
    JdbcTemplate jdbcTemplate;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler;
    @InjectMocks
    private OfficialsFailureHandler customerFailureHandler;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @Mock
    private AuthenticationException exception;
    @Mock
    private MyBankUsersServices myBankOfficialsService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void onAuthenticationFailureTest() throws Exception {
        customerFailureHandler.setUseForward(true);
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("divija");
        customer.setCustomerAddress("ujire");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUsername("user");
        customer.setPassword("1234");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByUsernameStream("user")).thenReturn(customer);
        // Mocking the request parameters
        mockMvc.perform(MockMvcRequestBuilders.post("/web/")
                .param("username", customer.getUsername())
                .param("password", customer.getPassword()))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/web/?error=Username not found"));
    }
}
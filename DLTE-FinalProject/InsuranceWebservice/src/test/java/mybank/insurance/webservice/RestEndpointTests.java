package mybank.insurance.webservice;


import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.security.MyBankUsers;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import mybank.insurance.webservice.rest.controller.InsuranceController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RestEndpointTests {
    @Mock
    private InsuranceRepository insuranceRepository;

    @InjectMocks
    private InsuranceController insuranceController;

    private MockMvc mockMvc;

    @Mock
    MyBankUsersServices myBankUsersServices;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(insuranceController).build();
    }

    @Test
    @WithMockUser(username = "testUser")
    void testSearch_Success() throws Exception {
        String requestBody = "{\"customerId\":1,\"startLimit\":20000,\"endLimit\":60000}";

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        MyBankUsers customer = new MyBankUsers();
        customer.setCustomerId(123);
        customer.setCustomerName("Divija");
        customer.setCustomerAddress("Ujire");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("112233");
        customer.setAttempts(1);
        when(myBankUsersServices.findByUsernameStream("testUser")).thenReturn(customer);
        when(myBankUsersServices.getCustomerName("testUser")).thenReturn(String.valueOf(Collections.singletonList("Divija")));

        // Mock the successful deletion of payee
        doNothing().when(insuranceRepository).findByInsuranceCoverage(1, 20000, 60000);

        // Perform the DELETE request
        mockMvc.perform(delete("/payees/delete/payee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Payee Arundhathi deleted successfully"));

    }

//    // @Test
//    @WithMockUser(username = "testUser")
//    void testDeletePayee_NotFound() throws Exception {
//        String requestBody = "{\"payeeId\":1,\"senderAccountNumber\":987456789123,\"payeeAccountNumber\":123456789234,\"payeeName\":\"Arundhathi\"}";
//
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        when(authentication.getName()).thenReturn("testUser");
//
//        MyBankOfficials customer = new MyBankOfficials();
//        customer.setCustomerId(123);
//        customer.setCustomerName("Sanatah");
//        customer.setCustomerAddress("karkala");
//        customer.setCustomerStatus("active");
//        customer.setCustomerContact(8765432345L);
//        customer.setUsername("testUser");
//        customer.setPassword("12233");
//        customer.setAttempts(1);
//        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
//        when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(987456789123L));
//
//        // Mock the successful deletion of payee
//        doNothing().when(paymentTransferImplementation).deletePayeeAdded(1, 987456789123L, 123456789234L, "Arundhathi");
//
//        // Perform the DELETE request
//        mockMvc.perform(delete("/payees/delete")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string(""));
//    }
}

package mybank.insurance.webservice;

import com.mybank.dao.insurance.entity.Customer;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import mybank.insurance.webservice.mvc.ControllerMvc;
import mybank.insurance.webservice.security.MyBankApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class ControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    public ControllerMvc controllerMvc;
    @Mock
    InsuranceRepository insuranceRepository;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controllerMvc).build();
    }
    @Test
    public void testLandingPage() {
        String result = controllerMvc.landing();
        assertEquals("index", result);
    }
    @Test
    public void testShowErrorPage() {
        String result = controllerMvc.errorPage();
        assertEquals("error", result);
    }

    @Test
    public void testHomePage() {
        String result = controllerMvc.homePage();
        assertEquals("dashboard", result);
    }

    @Test
    public void testLoginError() {
        Model model = mock(Model.class);
        String result = controllerMvc.loginError(model);
        assertEquals("index", result);
        verify(model).addAttribute("error", true);
    }

    @Test
    public void testUpdating() {
        String result = controllerMvc.view();
        assertEquals("viewInsurance", result);
    }
    @Test
    public void testUpdatingCoverage() {
        String result = controllerMvc.filterByCoverage();
        assertEquals("filterByCoverage", result);
    }
}

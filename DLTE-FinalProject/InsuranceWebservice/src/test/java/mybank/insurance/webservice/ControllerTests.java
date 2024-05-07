package mybank.insurance.webservice;

import mybank.insurance.webservice.mvc.ControllerMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class ControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    public ControllerMvc controllerMvc;

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

package mybank.insurance.webservice;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.InsuranceAvailedException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import mybank.insurance.webservice.rest.controller.InsuranceController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class FindByCoverageTests {
    @Mock
    private InsuranceRepository insuranceRepository;
    @InjectMocks
    private InsuranceController insuranceController;
    @Mock
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCoverageSuccess() throws SQLException {
        int id = 101;
        Double minLimit = 10000D;
        Double maxLimit = 40000D;
        HttpServletResponse response = null;
        List<InsuranceAvailed> mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailed());
        when(insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit)).thenReturn(mockInsuranceList);
        // Act
        List<InsuranceAvailed> result = insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByCoverageRange() throws SQLException {
        Double minLimit = 35000D;
        Double maxLimit = 67000D;
        Integer id = 101;
        List<InsuranceAvailed> mockInsuranceList = new ArrayList<>();
        InsuranceAvailed mockInsuranceAvailed = new InsuranceAvailed();
        mockInsuranceAvailed.setCustomerId(101);
        mockInsuranceList.add(mockInsuranceAvailed);
        when(insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit)).thenReturn(mockInsuranceList);

        List<InsuranceAvailed> result = insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getCustomerId());
    }

    @Test
    public void testFilterByCoverageRange() throws SQLException {
        Double minLimit = 35000D;
        Double maxLimit = 67000D;
        Integer id = 101;
        List<InsuranceAvailed> mockInsuranceList = new ArrayList<>();
        InsuranceAvailed mockInsuranceAvailed = new InsuranceAvailed();
        mockInsuranceAvailed.setCustomerId(101);
        // Add insurance with coverage within the range
        mockInsuranceList.add(mockInsuranceAvailed);
        when(insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit)).thenReturn(mockInsuranceList);

        List<InsuranceAvailed> result = insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getCustomerId());
    }


    @Test
    public void testFindByCoveragesRange() throws SQLException {
        Double minLimit = 20000D;
        Double maxLimit = 30000D;
        Integer id = 101;
        // No insurances within this range
        when(insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit)).thenReturn(Collections.emptyList());

        List<InsuranceAvailed> result = insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByCoverageRangeCoverageRange() throws SQLException {
        Double minLimit = 70000D;
        Double maxLimit = 80000D;
        Integer id = 101;
        // No insurances within this range
        when(insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit)).thenReturn(Collections.emptyList());

        List<InsuranceAvailed> result = insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertNotEquals(result.size(), 4);
    }

    @Test
    public void testFindAmountsInRangeFailure() throws SQLException {
        Double minLimit = 35000D;
        Double maxLimit = 67000D;
        Integer id = 101;
        List<InsuranceAvailed> mockInsuranceList = new ArrayList<>();
        InsuranceAvailed mockInsuranceAvailed = new InsuranceAvailed();
        mockInsuranceAvailed.setCustomerId(101);
        mockInsuranceList.add(mockInsuranceAvailed);
        mockInsuranceList.add(mockInsuranceAvailed);
        when(insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit)).thenReturn(mockInsuranceList);
        List<InsuranceAvailed> result = insuranceRepository.findByInsuranceCoverage(id, minLimit, maxLimit);

        assertNotNull(result);
        assertNotEquals(1, result.size());
        assertEquals(101, result.get(1).getCustomerId());
    }

    @Test
    public void testIsValidStartLimitValid() {
        String validStartLimit = "20000";
        assertTrue(insuranceController.isValidStartLimit(validStartLimit));
    }

    @Test
    public void testIsValidStartLimitValidWithDecimal() {
        String validStartLimit = "20000.50";
        assertTrue(insuranceController.isValidStartLimit(validStartLimit));
    }

    @Test
    public void testIsValidStartLimitInvalid() {
        String invalidStartLimit = "abc";
        assertFalse(insuranceController.isValidStartLimit(invalidStartLimit));
    }

    @Test
    public void testIsValidStartLimitNull() {
        String nullStartLimit = null;
        assertFalse(insuranceController.isValidStartLimit(nullStartLimit));
    }

    @Test
    public void testIsValidStartLimitEmpty() {
        String emptyStartLimit = "";
        assertFalse(insuranceController.isValidStartLimit(emptyStartLimit));
    }

    @Test
    public void testIsValidEndLimitValid() {
        String validEndLimit = "45000";
        assertTrue(insuranceController.isValidEndLimit(validEndLimit));
    }

    @Test
    public void testIsValidEndLimitValidWithDecimal() {
        String validEndLimit = "45000.75";
        assertTrue(insuranceController.isValidEndLimit(validEndLimit));
    }

    @Test
    public void testIsValidEndLimitInvalid() {
        String invalidEndLimit = "xyz";
        assertFalse(insuranceController.isValidEndLimit(invalidEndLimit));
    }

    @Test
    public void testIsValidEndLimitNull() {
        String nullEndLimit = null;
        assertFalse(insuranceController.isValidEndLimit(nullEndLimit));
    }

    @Test
    public void testIsValidEndLimitEmpty() {
        String emptyEndLimit = "";
        assertFalse(insuranceController.isValidEndLimit(emptyEndLimit));
    }

    @Test
    public void testAllInsuranceExceptionFailure() {
        List<InsuranceAvailable> insuranceAvailable = new ArrayList<>();
        insuranceAvailable.add(new InsuranceAvailable(1, "life", "maxlife", "lifetime coverage", 10));
        insuranceAvailable.add(new InsuranceAvailable(2, "vehicle", "bajaj", "cashless claim settlement", 5));
        insuranceAvailable.add(new InsuranceAvailable(3, "travel", "Tata", "Trip cancellation benefits", 1));
        insuranceAvailable.add(new InsuranceAvailable(4, "health", "reliance", "Accidental death cover", 3));
        assertDoesNotThrow(() -> insuranceRepository.allAvailableInsurance());
    }

}

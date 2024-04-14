package mybank.insurance.webservice.rest.controller;

import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import mybank.insurance.webservice.exceptions.InsuranceAvailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.insurance.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/module")
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;
    Logger logger = LoggerFactory.getLogger(InsuranceController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");


//    @GetMapping("/insurance/{startLimit}/{endLimit}")
//    public List<InsuranceAvailed> findByInsuranceCoverage(@PathVariable("startLimit") double startLimit, @PathVariable("endLimit") double endLimit, HttpServletResponse response) throws SQLSyntaxErrorException, NoDataFoundException {
//        ServiceStatus serviceStatus = new ServiceStatus();
//        List<InsuranceAvailed> insurance = null;
//        try {
//            insurance = insuranceRepository.findByInsuranceCoverage(startLimit,endLimit);
//        } catch (InsuranceAvailedException noDataFoundException) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            logger.error(resourceBundle.getString("insurance.data.null"));
//            System.out.println(noDataFoundException.getMessage());
//
//        }
//        return insurance;
//    }

    @GetMapping("/insurance/{startLimit}/{endLimit}")
    public ResponseEntity<List<InsuranceAvailed>> findByInsuranceCoverage(@PathVariable("startLimit") double startLimit, @PathVariable("endLimit") double endLimit) {
        List<InsuranceAvailed> insurance;
        try {
            insurance = insuranceRepository.findByInsuranceCoverage(startLimit, endLimit);
            if (insurance.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(insurance);
        } catch (Exception e) {  // Catch any other unexpected exceptions
            logger.error("Error retrieving insurance coverage data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
package mybank.insurance.webservice.rest.controller;

import com.mybank.dao.insurance.entity.Customer;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.InsuranceAvailedException;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.security.MyBankUsers;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.insurance.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/module")
@ComponentScan("com.mybank.dao.insurance")
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    MyBankUsersServices services;
    Logger logger = LoggerFactory.getLogger(InsuranceController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public InsuranceController(InsuranceRepository insuranceRepository) {
    }

    @GetMapping("/insurance/{startLimit}/{endLimit}")
    public ResponseEntity<?> findByInsuranceCoverage(@PathVariable("startLimit") double startLimit, @PathVariable("endLimit") double endLimit) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        MyBankUsers customer=services.findByUsernameStream(username);
        List<InsuranceAvailed> insurance;
        try {
            insurance = insuranceRepository.findByInsuranceCoverage(customer.getCustomerId(), startLimit, endLimit);
            if (insurance.size() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no data found");
            }
            return ResponseEntity.ok(insurance);
        }catch (InsuranceAvailedException | SQLException noDataFound) {
            logger.warn(resourceBundle.getString("insurance.data.null"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no data found");
        }
        catch (Exception exception ) {
            logger.error(resourceBundle.getString("insurance.sql.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

}
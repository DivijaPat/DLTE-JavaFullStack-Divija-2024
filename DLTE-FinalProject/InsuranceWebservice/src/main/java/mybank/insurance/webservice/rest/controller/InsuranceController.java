package mybank.insurance.webservice.rest.controller;


import com.mybank.dao.insurance.entity.Customer;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.InsuranceAvailedException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
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
import java.sql.SQLException;
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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("app");

    @GetMapping("/insurance/{startLimit}/{endLimit}")
    public ResponseEntity<?> findByInsuranceCoverage(@PathVariable("startLimit") String minLimit, @PathVariable("endLimit") String maxLimit) {
        String username = getUser();
        Customer customer=services.findByUsernameStream(username);
        List<InsuranceAvailed> insurance;
        try {
            if (!isValidStartLimit(minLimit)&&!isValidEndLimit(maxLimit)) {
                return ResponseEntity.badRequest().body(resourceBundle.getString("enter.proper.limits"));
            }
            Double startLimit=Double.valueOf(minLimit);
            Double endLimit=Double.valueOf(maxLimit);
            insurance = insuranceRepository.findByInsuranceCoverage(customer.getCustomerId(), startLimit, endLimit);
            return ResponseEntity.ok(insurance);
        }catch (InsuranceAvailedException | SQLException noDataFound) {
            logger.warn(resourceBundle.getString("insurance.data.null"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.one")+resourceBundle.getString("insurance.data.null"));
        }
        catch (Exception exception ) {
            logger.error(resourceBundle.getString("insurance.sql.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    public boolean isValidStartLimit(String startlimit){
        return startlimit != null && !startlimit.isEmpty() && startlimit.matches("^\\d*\\.?\\d+$");
    }
    public boolean isValidEndLimit(String endLimit){
        return endLimit != null && !endLimit.isEmpty() && endLimit.matches("^\\d*\\.?\\d+$");
    }
    @GetMapping("/name")
    public String getCustomerName() {
        String name = getUser();
        String user = services.getCustomerName(name);
        return user;
    }

    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return name;
    }



}
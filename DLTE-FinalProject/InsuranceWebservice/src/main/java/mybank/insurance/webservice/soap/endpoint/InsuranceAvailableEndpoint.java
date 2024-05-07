package mybank.insurance.webservice.soap.endpoint;


import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.insurance.CallAllInsuranceAvailableRequest;
import services.insurance.CallAllInsuranceAvailableResponse;
import services.insurance.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("com.mybank.dao.insurance.remotes.InsuranceRepository")
public class InsuranceAvailableEndpoint {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("app");
    Logger LOGGER = LoggerFactory.getLogger(InsuranceRepository.class);
    private final String url="http://insurance.services";
    //http://localhost:8082/insurancerepo/insurance.wsdl

    @Autowired
    private InsuranceRepository availableDbRepo;

    @PayloadRoot(namespace = url, localPart = "callAllInsuranceAvailableRequest")
    @ResponsePayload
    public CallAllInsuranceAvailableResponse listInsurance(@RequestPayload CallAllInsuranceAvailableRequest availableRequest) throws SQLSyntaxErrorException {
        CallAllInsuranceAvailableResponse availableResponse=new CallAllInsuranceAvailableResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        try {
        List<InsuranceAvailable> insuranceDao=availableDbRepo.allAvailableInsurance();
        List<services.insurance.InsuranceAvailable> actualInsurance=new ArrayList<>();

        insuranceDao.forEach(each->{
            services.insurance.InsuranceAvailable insuranceAvailable=new services.insurance.InsuranceAvailable();
            // Copy properties from each InsuranceAvailable object
            BeanUtils.copyProperties(each,insuranceAvailable);
            actualInsurance.add(insuranceAvailable);
        });

            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("soap.status.ok"));
            LOGGER.info(resourceBundle.getString("soap.status.ok")+HttpServletResponse.SC_OK);
            availableResponse.getInsurance().addAll(actualInsurance);
        }
        catch (SQLSyntaxErrorException sqlException) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOGGER.error(resourceBundle.getString("soap.sql.error") + sqlException + HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            serviceStatus.setMessage(resourceBundle.getString("soap.db.error"));
        } catch (Exception exception) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOGGER.error(resourceBundle.getString("soap.unknown.error")+ exception + HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(resourceBundle.getString("soap.unknown.error"));
        }

        availableResponse.setServiceStatus(serviceStatus);
        return availableResponse;
    }




}
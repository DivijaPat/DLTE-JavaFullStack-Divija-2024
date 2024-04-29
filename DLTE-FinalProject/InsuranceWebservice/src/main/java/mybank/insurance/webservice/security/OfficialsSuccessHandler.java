package mybank.insurance.webservice.security;

import com.mybank.dao.insurance.security.MyBankUsers;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OfficialsSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankUsersServices service;

    Logger logger= LoggerFactory.getLogger(OfficialsSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankUsers myBankUsers= (MyBankUsers) authentication.getPrincipal();
        if(myBankUsers.getCustomerStatus().equalsIgnoreCase("active")){
            if(myBankUsers.getAttempts()>1){
                myBankUsers.setAttempts(1);
                service.updateAttempts(myBankUsers);
            }
//            super.setDefaultTargetUrl("/insurancerepo/insurance.wsdl");
            super.setDefaultTargetUrl("/login/dashboard");
        }
        else{
            logger.warn("contact admin to activate");
            super.setDefaultTargetUrl("/login/?error="+"Account suspended contact admin to redeem\n");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

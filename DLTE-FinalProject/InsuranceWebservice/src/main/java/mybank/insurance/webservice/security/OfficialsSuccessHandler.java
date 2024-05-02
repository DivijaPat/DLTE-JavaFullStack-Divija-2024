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
import java.util.ResourceBundle;

@Component
public class OfficialsSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankUsersServices service;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("app");
    Logger logger= LoggerFactory.getLogger(OfficialsSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankUsers myBankUsers= (MyBankUsers) authentication.getPrincipal();
        if(myBankUsers.getCustomerStatus().equalsIgnoreCase("active")){
            if(myBankUsers.getAttempts()>1){
                myBankUsers.setAttempts(1);
                service.updateAttempts(myBankUsers);
            }
            super.setDefaultTargetUrl("/web/dashboard");
        }
        else{
            logger.warn(resourceBundle.getString("contact.to.activate"));
            super.setDefaultTargetUrl("/web/?errors="+resourceBundle.getString("contact.admin"));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

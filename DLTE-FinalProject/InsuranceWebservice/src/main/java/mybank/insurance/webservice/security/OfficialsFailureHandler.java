package mybank.insurance.webservice.security;

import com.mybank.dao.insurance.security.MyBankUsers;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OfficialsFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    MyBankUsersServices service;

    Logger logger = LoggerFactory.getLogger(OfficialsFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        try {
            MyBankUsers myBankUsers = service.findByUsernameStream(username);
            if (myBankUsers != null) {
                if (myBankUsers.getCustomerStatus().equalsIgnoreCase("active")) {
                    if (myBankUsers.getAttempts() < myBankUsers.getMaxAttempts()) {
                        myBankUsers.setAttempts(myBankUsers.getAttempts() + 1);
                        service.updateAttempts(myBankUsers);
                        logger.warn("Invalid credentials and attempts taken");
                        exception = new LockedException((4 - myBankUsers.getAttempts()) + " Attempts remaining");
                        String err = myBankUsers.getAttempts().toString() + " " + exception.getMessage();
                        logger.warn(err);
                        super.setDefaultFailureUrl("/login/?error=" + err);

                    } else {
                        service.updateStatus(myBankUsers);
                        logger.warn("account suspended");
                        exception = new LockedException("Max Attempts reached account is suspended");
                        super.setDefaultFailureUrl("/login/?error=" + exception.getMessage());

                    }
                }
//            else{
//                logger.warn("Account suspended contact admin to redeem");
//            }
                else {
                    super.setDefaultFailureUrl("/login/?error=User not exists");
                }
            }
        } catch (UsernameNotFoundException e) {
            logger.info(e.toString());
            logger.warn("account suspended");
            exception = new LockedException("Username not found");
            super.setDefaultFailureUrl("/login/?error=" + exception.getMessage());
        }
        super.onAuthenticationFailure(request, response, exception);

    }
}

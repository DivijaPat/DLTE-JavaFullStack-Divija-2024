package auth;


import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OfficialsFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    MyBankOfficialsService service;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyBankOfficials myBankOfficials = service.findByUsername(username);
        if(myBankOfficials!=null){
            if(myBankOfficials.getStatus()!=0){
                if(myBankOfficials.getAttempts()< myBankOfficials.getMaxAttempt()){
                    myBankOfficials.setAttempts(myBankOfficials.getAttempts()+1);
                    service.updateAttempts(myBankOfficials);
                    logger.warn("Invalid credentials and attempts taken");
                    exception=new LockedException("Attempts are taken");
                    String err = myBankOfficials.getAttempts()+" "+exception.getMessage();
                    logger.warn(err);
                    super.setDefaultFailureUrl("/web/?error="+err);
                }
                else{
                    service.updateStatus(myBankOfficials);
                    exception=new LockedException("Max Attempts reached account is suspended");
                    super.setDefaultFailureUrl("/web/?error="+exception.getMessage());
                }
            }
        }
        else{
            super.setDefaultFailureUrl("/web/?error=User not exists");
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
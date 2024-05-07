package mybank.insurance.webservice.security;

import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    @Autowired
    private MyBankUsersServices services;
    AuthenticationManager manager;
    @Autowired
    OfficialsFailureHandler officialsFailureHandler;
    @Autowired
    OfficialsSuccessHandler officialsSuccessHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();
        httpSecurity.formLogin().loginPage("/web/").usernameParameter("username").failureHandler(officialsFailureHandler).successHandler(officialsSuccessHandler);
        httpSecurity.authorizeRequests().antMatchers("/profiles/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/web/").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/pictures/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/styles/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/insurancerepo/insurance.wsdl").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.logout().permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(services);
        manager = builder.build();
        httpSecurity.authenticationManager(manager);

        return httpSecurity.build();

    }

}


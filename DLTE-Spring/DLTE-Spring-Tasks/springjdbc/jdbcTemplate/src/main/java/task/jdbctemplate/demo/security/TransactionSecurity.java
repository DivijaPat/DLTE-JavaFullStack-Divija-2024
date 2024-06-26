package task.jdbctemplate.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TransactionSecurity {

    @Autowired
    private MyBankUserServices services;
    AuthenticationManager manager;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.formLogin();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/transaction/add").hasAnyAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers("/transaction/send/*").hasAnyAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers("/transaction/receive/*").hasAnyAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers("/transaction/amount/*").hasAnyAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasAnyAuthority("manager","admin");
        httpSecurity.authorizeRequests().antMatchers("/transaction/deleteBasedOnRangeOfDate").hasAnyAuthority("manger","admin");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAnyAuthority("admin");

        AuthenticationManagerBuilder builder=httpSecurity.
                getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(services);
        manager=builder.build();
        httpSecurity.authenticationManager(manager);

        return httpSecurity.build();
    }
}
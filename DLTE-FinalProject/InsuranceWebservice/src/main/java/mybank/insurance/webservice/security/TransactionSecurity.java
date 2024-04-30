package mybank.insurance.webservice.security;//package com.payment.webservices.security;

import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@Configuration
public class TransactionSecurity {

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
    ResourceBundle resourceBundle=ResourceBundle.getBundle("app");
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();
        httpSecurity.formLogin().loginPage("/login/").
                usernameParameter("username").
                failureHandler(officialsFailureHandler).
                successHandler(officialsSuccessHandler);        httpSecurity.authorizeRequests().antMatchers("/profiles/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/login/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/pictures/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/styles/**").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().anyRequest().authenticated();

        httpSecurity.cors();
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(services);
        manager = builder.build();
        httpSecurity.authenticationManager(manager);

        return httpSecurity.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList(resourceBundle.getString("security.url")));

        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}


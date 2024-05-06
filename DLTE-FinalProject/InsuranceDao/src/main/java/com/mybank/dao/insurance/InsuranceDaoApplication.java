package com.mybank.dao.insurance;

import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class InsuranceDaoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(InsuranceDaoApplication.class, args);
        InsuranceRepository availableDbRepo = context.getBean(InsuranceRepository.class);

    }
}
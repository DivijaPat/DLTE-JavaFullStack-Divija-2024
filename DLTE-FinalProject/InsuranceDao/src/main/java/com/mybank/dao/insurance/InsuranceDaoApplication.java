package com.mybank.dao.insurance;

import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import com.mybank.dao.insurance.services.InsuranceServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLSyntaxErrorException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class InsuranceDaoApplication {

    public static void main(String[] args) throws SQLSyntaxErrorException, InsuranceAvailableException, NoDataFoundException {

        ConfigurableApplicationContext context = SpringApplication.run(InsuranceDaoApplication.class, args);
        InsuranceRepository availableDbRepo = context.getBean(InsuranceRepository.class);

//        try {
//            System.out.println(availableDbRepo.allAvailableInsurance());
//            System.out.println(availableDbRepo.findByInsuranceCoverage(30000,50000));
//        } catch (InsuranceAvailableException exception) {
//            System.out.println(exception);
//        }

    }
}
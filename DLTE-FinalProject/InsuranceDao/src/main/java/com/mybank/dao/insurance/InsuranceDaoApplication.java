package com.mybank.dao.insurance;

import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
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

    public static void main(String[] args) throws SQLSyntaxErrorException, InsuranceAvailableException {

        ConfigurableApplicationContext context = SpringApplication.run(InsuranceDaoApplication.class, args);
        InsuranceRepository availableDbRepo = context.getBean(InsuranceRepository.class);
        try {
            System.out.println(availableDbRepo.allAvailableInsurance());
        } catch (InsuranceAvailableException exception) {
            System.out.println(exception);
        }

    }
}
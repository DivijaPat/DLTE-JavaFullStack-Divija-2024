package com.mybank.dao.insurance.services;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;

/* This service retrieves all the record from the oracle db and returns the list of the records.
 This service also throws the required exception if encountered.*/

@Service
public class InsuranceServices implements InsuranceRepository {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    Logger LOGGER = LoggerFactory.getLogger(InsuranceRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<InsuranceAvailable> allAvailableInsurance() throws SQLSyntaxErrorException, InsuranceAvailableException {
        List<InsuranceAvailable> insuranceList=null;
        try {
            //retrieve the insurance list
            insuranceList = jdbcTemplate.query("select * from MYBANK_APP_INSURANCEAVAILABLE", new CardMapper());

        } catch (DataAccessException sqlException) {
           LOGGER.error(resourceBundle.getString("insurance.sql.error"), sqlException);
            throw new SQLSyntaxErrorException(sqlException);
        }

        //if the list is empty
        if(insuranceList.size()==0){
            LOGGER.warn(resourceBundle.getString("insurance.data.null"));
            throw new InsuranceAvailableException(resourceBundle.getString("insurance.data.null"));
        }
        return insuranceList;
    }

    @Override
    public List<InsuranceAvailed> findByInsuranceCoverage(double insuranceCoverage) {
        return null;
    }

    public class CardMapper implements RowMapper<InsuranceAvailable> {

        @Override
        public InsuranceAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            //create an instance of InsuranceAvailable
            InsuranceAvailable available = new InsuranceAvailable();

            //Set properties of the InsuranceAvailable object from the ResultSet
            available.setInsuranceId(rs.getInt(1));
            available.setInsuranceType(rs.getString(2));
            available.setInsuranceName(rs.getString(3));
            available.setInsuranceKeyBenefits(rs.getString(4));
            available.setInsuranceLifetime(rs.getInt(5));
            return available;
        }
    }
}
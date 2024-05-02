package com.mybank.dao.insurance.services;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
import com.mybank.dao.insurance.exceptions.InsuranceAvailedException;
import com.mybank.dao.insurance.exceptions.NoDataFoundException;
import com.mybank.dao.insurance.remotes.InsuranceRepository;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;



/* This service retrieves all the record from the oracle db and returns the list of the records.
 This service also throws the required exception if encountered.*/

@Service
public class InsuranceServices implements InsuranceRepository {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(InsuranceRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<InsuranceAvailable> allAvailableInsurance() throws SQLSyntaxErrorException, NoDataFoundException {
        List<InsuranceAvailable> insuranceList = null;
        try {
            //retrieve the insurance list
            insuranceList = jdbcTemplate.query("select * from MYBANK_APP_INSURANCEAVAILABLE", new CardMapper());

        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("insurance.sql.error"), sqlException);
            throw new SQLSyntaxErrorException(sqlException);
        }

        //if the list is empty
        if (insuranceList.size() == 0) {
            logger.warn(resourceBundle.getString("insurance.data.null"));
            throw new NoDataFoundException(resourceBundle.getString("insurance.data.null"));
        }
        return insuranceList;
    }


//    public class InsuranceAvailedRowMapper implements RowMapper<InsuranceAvailed> {
//        @Override
//        public InsuranceAvailed mapRow(ResultSet rs, int rowNum) throws SQLException {
//            InsuranceAvailed avail = new InsuranceAvailed();
//            avail.setInsuranceAvailedId(rs.getInt("INSURANCE_AVAIL_ID"));
//            avail.setCustomerId(rs.getInt("CUSTOMER_ID"));
//            avail.setInsuranceId(rs.getInt("INSURANCE_ID"));
//            avail.setInsurancePremium(rs.getDouble("INSURANCE_PREMIUM"));
//            avail.setInsuranceType(rs.getString("INSURANCE_TYPE"));
//            avail.setInsuranceName(rs.getString("INSURANCE_NAME"));
//            avail.setInsuranceKeyBenefits(rs.getString("INSURANCE_KEY_BENEFITS"));
//            avail.setInsuranceLifetime(rs.getInt("INSURANCE_LIFETIME"));
//            return avail;
//        }
//    }



    @Override
    public List<InsuranceAvailed> findByInsuranceCoverage(int customerId, double startLimit, double endLimit) throws SQLException {


        try {
            CallableStatementCreator callableStatement = conn -> {
                CallableStatement statement = conn.prepareCall("{call fetch_insurance_data(?, ?, ?, ?)}");
                statement.setDouble(1, startLimit);
                statement.setDouble(2, endLimit);
                statement.setInt(3, customerId);
                statement.registerOutParameter(4, OracleTypes.CURSOR);
                return statement;
            };

            Map<String, Object> returnedExecution = jdbcTemplate.call(callableStatement, Arrays.asList(
                    new SqlParameter[]{
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.INTEGER),
                            new SqlOutParameter(" insurancedata", OracleTypes.CURSOR)
                    }
            ));

            ArrayList<InsuranceAvailed> result = (ArrayList<InsuranceAvailed>) returnedExecution.get(" insurancedata");
            if (result.size() == 0) {
                throw new InsuranceAvailedException(resourceBundle.getString("insurance.data.null"));
            }
            return result;
        }catch (Exception exception){
            throw new SQLException();
        }

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

//    public class CardMapperAvailed implements RowMapper<InsuranceAvailed> {
//
//        @Override
//        public InsuranceAvailed mapRow(ResultSet rs, int rowNum) throws SQLException {
//            //create an instance of InsuranceAvailable
//            InsuranceAvailed availed = new InsuranceAvailed();
//
//            //Set properties of the InsuranceAvailable object from the ResultSet
//            availed.setInsuranceAvailedId(rs.getInt(1));
//            availed.setCustomerId(rs.getInt(2));
//            availed.setInsuranceId(rs.getInt(3));
//            availed.setInsuranceCoverage(rs.getDouble(4));
//            availed.setInsurancePremium(rs.getDouble(5));
//            availed.setInsuranceType(rs.getString(6));
//            availed.setInsuranceName(rs.getString(7));
//            availed.setInsuranceKeyBenefits(rs.getString(8));
//            availed.setInsuranceLifetime(rs.getInt(9));
//            return availed;
//        }
//    }
}


























package com.mybank.dao.insurance.remotes;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface InsuranceRepository {
     List<InsuranceAvailable> allAvailableInsurance() throws SQLSyntaxErrorException;
     List<InsuranceAvailed> findByInsuranceCoverage(int customerId,double startLimit,double endLimit) throws SQLException;
}

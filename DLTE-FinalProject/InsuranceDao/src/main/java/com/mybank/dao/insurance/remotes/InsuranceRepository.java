package com.mybank.dao.insurance.remotes;

import com.mybank.dao.insurance.entity.InsuranceAvailable;
import com.mybank.dao.insurance.entity.InsuranceAvailed;
import com.mybank.dao.insurance.exceptions.InsuranceAvailableException;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface InsuranceRepository {
     List<InsuranceAvailable> allAvailableInsurance() throws SQLSyntaxErrorException, InsuranceAvailableException;
     List<InsuranceAvailed> findByInsuranceCoverage(double insuranceCoverage);
}

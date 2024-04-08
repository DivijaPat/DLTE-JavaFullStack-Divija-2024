package spring.autowire.dltespringautowire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.autowire.dltespringautowire.model.Loan;
import spring.autowire.dltespringautowire.remote.MyLoanInterface;

import java.util.List;

@Service
public class MyBank {
    @Autowired
    @Qualifier("HomeLoanApplicants")
    private MyLoanInterface myLoanInterface;
    public List<Loan> execute() {
        List<Loan> homeLoanList = myLoanInterface.findAll();
        return homeLoanList;
    }
}

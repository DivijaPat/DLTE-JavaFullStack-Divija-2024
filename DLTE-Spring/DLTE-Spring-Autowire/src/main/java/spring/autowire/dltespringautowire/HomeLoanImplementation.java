package spring.autowire.dltespringautowire;

import org.springframework.stereotype.Component;
import spring.autowire.dltespringautowire.model.Loan;
import spring.autowire.dltespringautowire.remote.MyLoanInterface;

import java.util.ArrayList;
import java.util.List;

@Component("HomeLoanApplicants")
public class HomeLoanImplementation implements MyLoanInterface {
    @Override
    public List<Loan> findAll() {
        List<Loan> homeLoanList = new ArrayList<>();
        for(Loan loan:myLoan){
            if(loan.getLoanType().equalsIgnoreCase("home loan"))
                  homeLoanList.add(loan);
        }
        return homeLoanList;
    }
}

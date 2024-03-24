package com.example.demo.implementations;

import com.example.demo.interfaces.LoanInterface;
import com.example.demo.model.Loan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("personalImplementation")
public class PersonalLoanImplementation implements LoanInterface {
    List<Loan> personalLoanList=new ArrayList<>();
    @Override
    public List<Loan> findAll() {
        for(Loan loan:loanList){
            if(loan.getLoanType().equalsIgnoreCase("personal"))
                personalLoanList.add(loan);
        }
        return personalLoanList;
    }
}
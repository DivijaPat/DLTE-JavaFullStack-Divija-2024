package com.example.demo;

import com.example.demo.interfaces.LoanInterface;
import com.example.demo.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyBank {
    @Autowired
    @Qualifier("homeImplementation")
    private LoanInterface loanInterface;
    public List<Loan> callFindAll(){
        List<Loan> fieldInjectedLoan=loanInterface.findAll();
        return fieldInjectedLoan;
    }
}
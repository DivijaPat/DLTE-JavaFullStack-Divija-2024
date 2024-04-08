package com.autowire.taskautowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
//public class MyBank {
//    @Autowired
//    @Qualifier("personalLoan")
//    private LoanImplementation loanImplementation;
//    public List<Loan> callFindAll() {
//        List<Loan> injectedLoan = loanImplementation.findALL();
//        return injectedLoan;
//    }
//}

@Component
public class MyBank {
    private  LoanImplementation loanImplementation;
    public MyBank(@Qualifier("homeLoan") LoanImplementation loanImplementation) {
        this.loanImplementation = loanImplementation;
    }

    public List<Loan> callFindAll() {
        return loanImplementation.findALL();
    }
}

//@Component
//public class MyBank {
//    private  LoanImplementation loanImplementation;
//
//    @Autowired
//    @Qualifier("homeLoan")
//    public void setHomeLoanDependency( LoanImplementation loanImplementation) {
//        this.loanImplementation= loanImplementation;
//    }
//
//    public List<Loan> callFindAll() {
//       return loanImplementation.findALL();
//    }
//}
package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface MyBank {
    ArrayList<Loan> loans=new ArrayList<>();
    void addLoan() throws IOException, ClassNotFoundException;
    void availableLoans() throws IOException, ClassNotFoundException;
    void closedLoans() throws IOException, ClassNotFoundException;


}

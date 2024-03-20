package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//http://localhost:8080/loan/
@RestController
@RequestMapping("/loan")
public class LoanRestController {
    List<Loan> loans =new ArrayList<>();

    public LoanRestController() {
        loans.add(new Loan(43455L,5355000,new Date("3/2/2024"),"closed"));
        loans.add(new Loan(45346L,2300000,new Date("1/12/2024"),"open"));
        loans.add(new Loan(62347L,1700000,new Date("2/12/2024"),"closed"));
        loans.add(new Loan(12348L,7400000,new Date("11/2/2024"),"open"));
    }

    @GetMapping("/{employeeIndex}")
    public Loan readOne(@PathVariable("employeeIndex") int index){
        return loans.get(index);
    }
    @PostMapping("/")
    public String addNewLoan(@RequestBody Loan loan){
        loans.add(loan);
        return "New Loan object "+loan+ " added";
    }
}
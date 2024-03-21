package com.accountsjpa.demo.controller;


import com.accountsjpa.demo.model.Accounts;
import com.accountsjpa.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsApi {
    @Autowired
    AccountService accountService;


    @PostMapping("/")
    public Accounts callAccountOpening(@RequestBody Accounts accounts){
        return accountService.AccountOpening(accounts);
    }

    @PutMapping("/")
    public  Accounts callAccountUpdate(@RequestBody Accounts accounts){
        return accountService.AccountOpening(accounts);
    }

    @GetMapping("/")
    public List<Accounts> callListAll(){
        return  accountService.AllAccounts();
    }
}
package com.accountsjpa.demo.services;

import com.accountsjpa.demo.model.Accounts;
import com.accountsjpa.demo.remotes.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountsRepository accountsRepository;


    public Accounts AccountOpening(Accounts accounts){
        return accountsRepository.save(accounts);
    }

    public List<Accounts> AllAccounts(){
        return (List<Accounts>) accountsRepository.findAll();
    }
}
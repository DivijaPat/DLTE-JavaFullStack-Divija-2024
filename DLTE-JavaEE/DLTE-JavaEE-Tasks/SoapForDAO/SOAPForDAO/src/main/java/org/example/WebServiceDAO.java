package org.example;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.exceptions.TransactionNotFoundException;
import org.example.middleware.DatabaseTarget;
import org.example.services.TransactionServices;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebServiceDAO {
    TransactionServices service;

    public WebServiceDAO() {
        service=new TransactionServices(new DatabaseTarget());
    }

    @WebResult(name = "getTransactionByUsername")
    public List<Transaction> getTransactionByUsername(String sender) throws AccountNotFoundException, TransactionNotFoundException {
       List<Transaction> transaction=service.getTransactionByUsername(sender);
        return transaction;
    }

    @WebResult(name="transferFunds")
    public Double transferFunds(String senderUsername,String receiverUsername,double amount) throws InsufficientFundsException, IOException, AccountNotFoundException, ReceiverNotFoundException {
        double balance=service.transferFunds(senderUsername,receiverUsername,amount);
        return balance;
    }
    @WebResult(name="addAccount")//method to create account
    public void addAccount(String username,String password,String email,long phoneNumber,double balance){
        service.addAccount(new Account(username,password,email,phoneNumber,balance));
    }

}
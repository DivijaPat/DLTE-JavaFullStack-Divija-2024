package org.webservices;

import org.example.Account;
import org.example.WebServicesDAO;
import org.example.WebServicesDAOService;
import org.example.entity.Transaction;

import java.util.List;

public class WebServiceClient {
    public static void main(String[] args) {
        WebServicesDAOService webServicesDAOService=new WebServicesDAOService();
        Account account=new Account();
        WebServicesDAO webServicesDAO=webServicesDAOService.getWebServicesDAOPort();
        webServicesDAO.addAccount("Ann","3423","Ann@gmail",7658456376L,70000D);//to add new account
        List<Transaction> transaction=webServicesDAO.getTransactionByUsername("medhini");//search bu username
        System.out.println("Name:"+account.getName()+"\npassword:"+account.getPassword()+"\nAccount Balance:"+account.getBalance());
        double balance=webServicesDAO.transferFunds("Ann","medhini",1000);
        System.out.println("Your transaction of Rs 1000 is deducted.Your current balance is "+balance);
    }
}
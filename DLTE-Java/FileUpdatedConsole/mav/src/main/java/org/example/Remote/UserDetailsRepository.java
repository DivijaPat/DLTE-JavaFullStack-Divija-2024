package org.example.Remote;

import org.example.Entity.Account;

import org.example.Exceptions.InsufficientFundsException;
import org.example.Exceptions.InvalidCredentialsException;
import org.example.Exceptions.ReceiverNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDetailsRepository {
    List<Account> getAllAccounts();
    Account getAccountByUsername(String username) throws AccountNotFoundException;
    void transferFunds(String sender, String receiver, double amount) throws InsufficientFundsException, ReceiverNotFoundException, IOException, AccountNotFoundException;
    void updateAccountBalance(String username, double newBalance) throws IOException, AccountNotFoundException;
    boolean authenticate(String username, String password) throws InvalidCredentialsException, SQLException;
    void addUsers();
}


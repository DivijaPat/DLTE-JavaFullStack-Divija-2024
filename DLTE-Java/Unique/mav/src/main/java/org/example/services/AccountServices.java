package org.example.services;

import org.example.entity.Account;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.remote.StorageTarget;
import org.example.remote.UserDetailsRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccountServices {
    UserDetailsRepository userDetailsRepository;
    StorageTarget storageTarget;
    public AccountServices(StorageTarget storageTarget) {
        userDetailsRepository = storageTarget.getUserDetailsRepository();
    }

    Account getAccountByUsername(String username) throws AccountNotFoundException {
        try {
            return userDetailsRepository.getAccountByUsername(username);
        } catch (AccountNotFoundException accountNotFoundException) {
            throw accountNotFoundException;
        }
    }
    public List<Account> getAllAccounts()
    {
        return userDetailsRepository.getAllAccounts();
    }

    public void transferFunds(String senderUsername, String receiverUsername, double amount) throws InsufficientFundsException, ReceiverNotFoundException, IOException, AccountNotFoundException {
        try {
            userDetailsRepository.transferFunds(senderUsername, receiverUsername, amount);
        } catch (InsufficientFundsException insufficientFundsException) {
            throw insufficientFundsException;
        } catch (ReceiverNotFoundException receiverNotFoundException) {
            throw receiverNotFoundException;
        } catch (IOException iOException) {
            throw iOException;
        } catch (AccountNotFoundException accountNotFoundException) {
            throw accountNotFoundException;
        }
    }

    void updateAccountBalance(String username,double newBalance) throws IOException, AccountNotFoundException {
        try {
            userDetailsRepository.updateAccountBalance(username,newBalance);
        } catch (IOException ioException) {
            throw ioException;
        } catch (AccountNotFoundException accountNotFoundException) {
            throw accountNotFoundException;
        }
    }

    public boolean authenticate(String username, String password) throws InvalidCredentialsException, SQLException {
        try {
            return userDetailsRepository.authenticate(username, password);
        } catch (InvalidCredentialsException invalidCredentialsException) {
            throw invalidCredentialsException;
        } catch (SQLException sqlException) {
            throw sqlException;
        }

    }

}

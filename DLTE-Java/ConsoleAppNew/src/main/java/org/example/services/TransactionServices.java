package org.example.services;
import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.exceptions.TransactionNotFoundException;
import org.example.remote.StorageTarget;
import org.example.remote.UserDetailsRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionServices {
    UserDetailsRepository userDetailsRepository;
    StorageTarget storageTarget;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public TransactionServices(StorageTarget storageTarget) {
        userDetailsRepository = storageTarget.getUserDetailsRepository();
    }

    public List<Transaction> getTransactionByUsername(String sender) throws TransactionNotFoundException, AccountNotFoundException {
        try {
//            System.out.println(userDetailsRepository.getTransactionByUsername(sender));
            List<Transaction> transactions = userDetailsRepository.getTransactionByUsername(sender);
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toString());
            }
            return null;
        }
        catch(TransactionNotFoundException transactionNotFoundException ) {
        throw transactionNotFoundException;
    }catch(AccountNotFoundException accountNotFoundException) {
        throw accountNotFoundException;
    }
}

    public List<Account> getAllAccounts()
    {
        return userDetailsRepository.getAllAccounts();
    }

    public List<Transaction> getAllTransactions() {
        return userDetailsRepository.getAllTransactions();
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

    public void updateAccountBalance(Account account) throws IOException, AccountNotFoundException {
        try {
            userDetailsRepository.updateAccountBalance(account);
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
    public void addAccount(Account account){
        userDetailsRepository.addAccount(account);
    }



}


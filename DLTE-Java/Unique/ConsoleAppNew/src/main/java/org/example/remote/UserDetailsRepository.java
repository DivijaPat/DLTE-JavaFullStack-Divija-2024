package org.example.remote;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.exceptions.TransactionNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDetailsRepository {
    List<Account> getAllAccounts();

    List<Transaction> getTransactionByUsername(String sender) throws AccountNotFoundException, TransactionNotFoundException;

    void transferFunds(String senderUsername, String receiverUsername, double amount) throws InsufficientFundsException, ReceiverNotFoundException, IOException, AccountNotFoundException;

    void updateAccountBalance(Account account) throws IOException, AccountNotFoundException;

    boolean authenticate(String username, String password) throws InvalidCredentialsException, SQLException;

    List<Transaction> getAllTransactions();

    void addAccount(Account account);
}
//String sender, String receiverUsername, double amount


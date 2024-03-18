package org.example.remotes;

import org.example.entity.Account;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;

public interface TransactionService {
    void transferFunds(String sender, String receiverUsername, double amount) throws InsufficientFundsException, ReceiverNotFoundException, IOException, AccountNotFoundException;
}


package org.example.services;
import org.example.entity.Account;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.remotes.AccountService;
import org.example.remotes.TransactionService;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
     private final AccountService accountService = null;

//    public TransactionServiceImpl(AccountService accountService) {
//        this.accountService = accountService;
//    }

    @Override
    public void transferFunds(String sender, String receiverUsername, double amount) throws InsufficientFundsException, ReceiverNotFoundException, IOException, AccountNotFoundException {
        Account receiver = accountService.getAccountByUsername(receiverUsername);
        if (receiver == null) {
            throw new ReceiverNotFoundException("Receiver not found for username: " + receiverUsername);
        }

        if (sender.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in sender's account");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        Double senders=sender.getBalance();


        String transactionInfo = String.format("Transferred %.2f from %s to %s", amount, sender.getUsername(), receiver.getUsername());
        System.out.println(transactionInfo);
        System.out.println("Remaining Balance: "+senders);
        sender.addToTransactionHistory(transactionInfo);
        receiver.addToTransactionHistory(transactionInfo);

        List<Account> accounts = accountService.getAllAccounts();
        accountService.updateAccounts(accounts);
        accountService.updateAccountBalance(sender.getUsername(), sender.getBalance());
        accountService.updateAccountBalance(receiver.getUsername(), receiver.getBalance());
    }
}

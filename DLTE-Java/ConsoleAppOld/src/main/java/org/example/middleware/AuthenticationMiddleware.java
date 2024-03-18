package org.example.middleware;

import org.example.entity.Account;
import org.example.exceptions.InvalidCredentialsException;
import org.example.remotes.AccountService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationMiddleware {
    private final AccountService accountService;
    private List<Account> accounts;

    public AuthenticationMiddleware(AccountService accountService) {
        this.accountService = accountService;
       // accounts = loadAccountsFromFile("account.txt");
    }

    private List<Account> loadAccountsFromFile(String filename) {
        List<Account> accounts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Account account = (Account) ois.readObject();
                    accounts.add(account);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public boolean authenticate(String username, String password) throws InvalidCredentialsException {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        throw new InvalidCredentialsException("Invalid username or password");
    }
}

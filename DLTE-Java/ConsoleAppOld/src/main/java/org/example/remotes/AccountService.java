package org.example.remotes;

import org.example.entity.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountByUsername(String username) throws AccountNotFoundException;
    void updateAccounts(List<Account> accounts) throws IOException;
    public void updateAccountBalance(String username, double newBalance) throws IOException, AccountNotFoundException;
}


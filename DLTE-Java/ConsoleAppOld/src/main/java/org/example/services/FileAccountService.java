package org.example.services;

import org.example.entity.Account;
import org.example.remotes.AccountService;

import javax.security.auth.login.AccountNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileAccountService implements AccountService {
    private final String filename;

    public FileAccountService(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Account> getAllAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Account> accounts = new ArrayList<>();
            while (true) {
                try {
                    Account account = (Account) ois.readObject();
                    accounts.add(account);
                } catch (EOFException e) {
                    break;
                }
            }
            return accounts;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Account getAccountByUsername(String username) throws AccountNotFoundException {
        List<Account> accounts = getAllAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found for username: " + username);
    }


    public void updateAccountBalance(String username, double newBalance) throws IOException, AccountNotFoundException {
        List<Account> accounts = getAllAccounts();
        boolean found = false;
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                account.setBalance(newBalance);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new AccountNotFoundException("Account not found for username: " + username);
        }
        updateAccounts(accounts);
    }
}

//public class FileAccountService implements AccountService {
//    private final String filename;
//
//    public FileAccountService(String filename) {
//        this.filename = filename;
//    }
//
//    @Override
//    public List<Account> getAllAccounts() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            List<Account> accounts = new ArrayList<>();
//            while (true) {
//                try {
//                    Account account = (Account) ois.readObject();
//                    accounts.add(account);
//                } catch (EOFException e) {
//                    break;
//                }
//            }
//            return accounts;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//
//    @Override
//    public Account getAccountByUsername(String username) throws AccountNotFoundException {
//        List<Account> accounts = getAllAccounts();
//        for (Account account : accounts) {
//            if (account.getUsername().equals(username)) {
//                return account;
//            }
//        }
//        throw new AccountNotFoundException("Account not found for username: " + username);
//    }
//
//    @Override
//    public void updateAccounts(List<Account> accounts) throws IOException {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//            for (Account account : accounts) {
//                oos.writeObject(account);
//            }
//        }
//    }
//}


package org.example.Middleware;


import org.example.Entity.Account;
import org.example.Exceptions.InsufficientFundsException;
import org.example.Exceptions.ReceiverNotFoundException;
import org.example.Remote.UserDetailsRepository;
import org.example.Exceptions.InvalidCredentialsException;
import org.example.Remote.UserDetailsRepository;


import javax.security.auth.login.AccountNotFoundException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//import org.slf4j.LoggerFactory;
public class UserDetailsFileRepository implements UserDetailsRepository {
    private String filePath;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("inputDetails");
    private Logger logger = Logger.getLogger(UserDetailsFileRepository.class.getName());
    private List<Account> accountList;
    private static Scanner scanner=new Scanner(System.in);

    public UserDetailsFileRepository(String url) {
        filePath = url;
        accountList = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                // If the file doesn't exist, create a new file
            }
            FileHandler fileHandler = new FileHandler("inputDetails.txt",true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public UserDetailsFileRepository() {

    }

    private void writeIntoFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Account account : accountList) {
                objectOutputStream.writeObject(account);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void readFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            accountList.clear(); // Clear the existing list before reading from file
            while (true) {
                try {
                    Account account = (Account) objectInputStream.readObject();
                    accountList.add(account);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public void addUsers() {
        accountList.add(new  Account("user1", "password1", "user1@example.com", 1234567890L, 1000.00));
        accountList.add(new  Account("user2", "password2", "user2@example.com", 9876543210L, 2000.00));
        accountList.add(new  Account("user3", "password3", "user3@example.com", 5555555555L, 3000.00));
        writeIntoFile();
    }
    @Override
    public boolean authenticate(String username, String password) {
        readFromFile();
        Account account = accountList.stream()
                .filter(each -> each.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        try {
            if (account == null) {
                System.out.println(resourceBundle.getString("username.not.found"));
                logger.log(Level.WARNING, resourceBundle.getString("username.not.found"));
                return false;
            } else if (!account.getPassword().equals(password)) {
                logger.log(Level.WARNING, resourceBundle.getString("password.not.matched"));
                System.out.println(resourceBundle.getString("password.not.matched"));
                System.out.println("Renter the password");
                throw new InvalidCredentialsException("Username or password is invalid");
            } else
                return true;
        }catch(InvalidCredentialsException invalidCedentialsException){
            for(int attempts=2;attempts<=3;){
                System.out.println(resourceBundle.getString("login.fail")+" Only "+(3-attempts+1)+" attempts left");
                logger.log(Level.WARNING,resourceBundle.getString("login.fail"));
                System.out.println(invalidCedentialsException);
                String pass=scanner.next();
                if(account.getPassword().equals(pass)){
                    System.out.println(resourceBundle.getString("login.success"));
                    logger.log(Level.INFO,resourceBundle.getString("login.success"));
                    return true;
                }else{
                    //   System.out.println(resourceBundle.getString("accounts.login.fail")+" Only "+(3-attempts)+" attempts left");;
                    attempts++;
                }if(attempts>3) {
                    System.out.println(resourceBundle.getString("accounts.no.more.attempts"));
                    logger.log(Level.WARNING,resourceBundle.getString("accounts.no.more.attempts"));
                    System.exit(0);
                }
            }
        }
        return false;
    }

    public List<Account> getAllAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
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
    public void updateAccounts(List<Account> accounts) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("account.txt"))) {
            for (Account account : accounts) {
                oos.writeObject(account);
            }
        }
    }
    public void transferFunds(String username, String receiverUsername, double amount) throws InsufficientFundsException, ReceiverNotFoundException, IOException, AccountNotFoundException {
        Account receiver = getAccountByUsername(receiverUsername);
        Account sender=getAccountByUsername(username);
        if (receiver == null) {
            throw new ReceiverNotFoundException("Receiver not found for username: " + receiverUsername);
        }

        if (sender.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in sender's account");
        }
        if (!sender.getUsername().equals(username)) {
            throw new IllegalStateException("Unauthorized transfer: You can only transfer funds from your own account.");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        Double senders=sender.getBalance();


        String transactionInfo = String.format("Transferred %.2f from %s to %s", amount, sender.getUsername(), receiver.getUsername());
        System.out.println(transactionInfo);
        System.out.println("Remaining Balance: "+senders);
//        if (sender != null) {
//            sender.addToTransactionHistory(transactionInfo); // Add null check here
//        }
//
//        if (receiver != null) {
//            receiver.addToTransactionHistory(transactionInfo); // Add null check here
//        }

        List<Account> account = getAllAccounts();
        updateAccounts(account);
        updateAccountBalance(sender.getUsername(), sender.getBalance());
        updateAccountBalance(receiver.getUsername(), receiver.getBalance());
    }
}

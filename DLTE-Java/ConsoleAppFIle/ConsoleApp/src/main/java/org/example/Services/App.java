package org.example.Services;

import org.example.Entity.Account;
import org.example.Exceptions.InsufficientFundsException;
import org.example.Exceptions.InvalidCredentialsException;
import org.example.Exceptions.ReceiverNotFoundException;
import org.example.Middleware.FileStorageTarget;
import org.example.Middleware.UserDetailsFileRepository;
import org.example.Remote.StorageTarget;


import javax.security.auth.login.AccountNotFoundException;
import java.io.*;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;


import static java.lang.System.exit;

/**
 * Hello world!
 *
 */
public class App {
    private static Account account;
    private static Logger logger = Logger.getLogger(UserDetailsFileRepository.class.getName());
    private static StorageTarget storageTarget;
    private static AccountServices services;
    private static Scanner scanner = new Scanner(System.in);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    List<Account> accountList = new ArrayList<>();

    public static void main(String[] args) throws InvalidCredentialsException, SQLException {

//               Account user1 = new Account("user1", "password1", "user1@example.com", 1234567890L, 1000.00);
//        Account user2 = new Account("user2", "password2", "user2@example.com", 9876543210L, 2000.00);
//        Account user3 = new Account("user3", "password3", "user3@example.com", 5555555555L, 3000.00);
//
//        List<Account> accounts = new ArrayList<>();
//        accounts.add(user1);
//        accounts.add(user2);
//        accounts.add(user3);
//
//
//        writeAccountsToFile(accounts, "inputDetail.txt");
//    }
//
//    private static void writeAccountsToFile(List<Account> accounts, String filename) {
//       UserDetailsFileRepository fileAccountService = new UserDetailsFileRepository(filename);
//        try {
//            fileAccountService.updateAccounts(accounts);
//            System.out.println("Accounts written to file successfully.");
//        } catch (IOException e) {
//            System.out.println("Error writing accounts to file: " + e.getMessage());
//        }
//    }
        boolean status = false;
        String username="", password;
        storageTarget = new FileStorageTarget();
        services = new AccountServices(storageTarget);

        try {
            int count = 0;
            while (count < 3) {
                System.out.println("Enter your username: ");
                username = scanner.nextLine();
                System.out.println("Enter your password: ");
                password = scanner.nextLine();
                boolean currentUser = services.authenticate(username, password);
                if (currentUser == true) {
                    System.out.println("Authentication successful!");
                    break;
                }
                count++;
            }
            if (count == 3) {
                System.out.println("Three attempts completed\nTry after 30 minutes");
                System.exit(0);
            }
            while (status) {
                System.out.println("Authentication successful!");
                System.out.println("\nMenu:");
                System.out.println(resourceBundle.getString("app.menu"));
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter receiver's username: ");
                        String receiverUsername = scanner.nextLine();
                        System.out.println("Enter amount to transfer: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Enter your password: ");
                        String password1 = scanner.nextLine();


                        boolean currentUser1 = false;
                        try {
                            currentUser1 = services.authenticate(username, password1);
                        } catch (InvalidCredentialsException ex) {
                            ex.printStackTrace();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        if (currentUser1) {
                            System.out.println("Authentication successful!");
                            try {
                                services.transferFunds(username, receiverUsername, amount);
                                System.out.println("Transaction successful!");
                            } catch (InsufficientFundsException | ReceiverNotFoundException | IOException e) {
                                System.out.println(e.getMessage());
                            } catch (AccountNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                        break;
                    case 2:
                        System.out.println(resourceBundle.getString("under.progress"));
                        break;
                    case 3:
                        System.out.println(resourceBundle.getString("under.progress"));
                        break;
                    case 4:
                        System.out.println(resourceBundle.getString("under.progress"));
                        break;
                    case 5:
                        System.out.println(resourceBundle.getString("under.progress"));
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        status = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InvalidCredentialsException e) {
            System.out.println(resourceBundle.getString("wrong.password"));
        }
    }
}





package org.example;
import org.example.entity.Account;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.TransactionNotFoundException;
import org.example.middleware.UserDetailsDatabaseRepository;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
//import org.example.services.TransactionServiceImpl;
import org.example.services.TransactionServices;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws InvalidCredentialsException, AccountNotFoundException, IOException, SQLException, TransactionNotFoundException {
       // AuthenticationMiddleware authMiddleware = new AuthenticationMiddleware(new FileAccountService("account.txt"));
//        //TransactionService transactionService = (TransactionService) new TransactionServiceImpl(new FileAccountService("account.txt"));
//        AuthenticationMiddleware authMiddleware = new AuthenticationMiddleware();
//        StorageTarget transactionService=new TransactionServiceImpl();
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the funds transfer system");
        StorageTarget storageTarget=new DatabaseTarget();
        TransactionServices userDetailsServices=new TransactionServices(storageTarget);
        UserDetailsDatabaseRepository userDetailsDatabaseRepository=new UserDetailsDatabaseRepository();
//        Account user1 = new Account("user1", "password1", "user1@example.com", 1234567890L, 1000.00);
//        Account user2 = new Account("user2", "password2", "user2@example.com", 9876543210L, 2000.00);
//        Account user3 = new Account("user3", "password3", "user3@example.com", 5555555555L, 3000.00);
//
//        List<Account> accounts = new ArrayList<>();
//        accounts.add(user1);
//        accounts.add(user2);
//        accounts.add(user3);
//

//        writeAccountsToFile(accounts, "account.txt");
//

//    }

//    private static void writeAccountsToFile(List<Account> accounts, String filename) {
//        FileAccountService fileAccountService = new FileAccountService(filename);
//        try {
//            fileAccountService.updateAccounts(accounts);
//            System.out.println("Accounts written to file successfully.");
//        } catch (IOException e) {
//            System.out.println("Error writing accounts to file: " + e.getMessage());
//        }
//    }
//
//        try {
//            System.out.println("Enter your username: ");
//            String username = scanner.nextLine();
//            System.out.println("Enter your password: ");
//            String password = scanner.nextLine();
//
//
//            Account currentUser = authMiddleware.authenticate(username, password);
//            System.out.println("Authentication successful!");
//
//            boolean running = true;
//            while (running) {
//                System.out.println("\nMenu:");
//                System.out.println("1. Transfer funds");
//                System.out.println("2. Exit");
//                System.out.println("Enter your choice: ");
//                int choice = scanner.nextInt();
//                scanner.nextLine();
//
//                switch (choice) {
//                    case 1:
//                        System.out.println("Enter receiver's username: ");
//                        String receiverUsername = scanner.nextLine();
//                        System.out.println("Enter amount to transfer: ");
//                        double amount = scanner.nextDouble();
//                        scanner.nextLine();
//
//                        try {
//                            transactionService.transferFunds(username, receiverUsername, amount);
//                            System.out.println("Transaction successful!");
//                        } catch (InsufficientFundsException | ReceiverNotFoundException | IOException e) {
//                            System.out.println(e.getMessage());
//                        } catch (AccountNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case 2:
//                        System.out.println("Exiting...");
//                        running = false;
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//            }
//        } catch (InvalidCredentialsException e) {
//            System.out.println("Invalid username or password");
//        }

         //  FileAccountService fileAccountService=new FileAccountService();
//           if(fileAccountService.isEstablished()) System.out.println("Connected");
//           else System.out.println("Failed");

//        userDetailsServices.addAccount(new Account("Akshira","123@","akshira@gmail",9967576855L,5000));
//        System.out.println(userDetailsServices.authenticate("divija","1234"));
//
//        userDetailsDatabaseRepository.transferFunds("divija","medhini",200);
//        System.out.println(userDetailsDatabaseRepository.getTransactionByUsername("divija"));
//        System.out.println(userDetailsDatabaseRepository.getAllTransactions());
//
//       // userDetailsDatabaseRepository.updateAccountBalance();
//        System.out.println(userDetailsDatabaseRepository.getAllAccounts());

    }
}

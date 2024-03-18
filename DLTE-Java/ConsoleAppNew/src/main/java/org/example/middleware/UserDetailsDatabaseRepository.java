package org.example.middleware;


import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.TransactionNotFoundException;
import org.example.remote.UserDetailsRepository;
//import org.slf4j.LoggerFactory;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class UserDetailsDatabaseRepository implements UserDetailsRepository {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
   // private Logger logger = Logger.getLogger(UserDetailsDatabaseRepository.class.getName());


    public UserDetailsDatabaseRepository(Connection connection) {
       // try{
            this.connection=connection;
           // FileHandler fileHandler=new FileHandler("credit-card-logs.txt",true);
            //SimpleFormatter simpleFormatter=new SimpleFormatter();
            //ileHandler.setFormatter(simpleFormatter);
           // logger.addHandler(fileHandler);
       // }
       // catch (IOException ioException){
        //    System.out.println(ioException);
      //  }
    }

    public UserDetailsDatabaseRepository() {
    }


    // @Override
//    public Object verifyPassword(String username, String password) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserDetails WHERE username=? AND password=?");
//            statement.setString(1, username);
//            statement.setString(2, password);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                UserDetails userDetails = new UserDetails(
//                        resultSet.getString("username"),
//                        resultSet.getString("password"),
//                        resultSet.getDate("dob"),
//                        resultSet.getString("address"),
//                        resultSet.getString("email"),
//                        resultSet.getLong("phone")
//                );
//                return userDetails;
//            } else {
//                logger.log(Level.WARNING, "Username or password incorrect.");
//                throw new UserDetailsException("Username or password incorrect.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public Account authenticate(String username, String password) throws InvalidCredentialsException {
//        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
//        try {
//            PreparedStatement statement = connection.prepareStatement(query) ;
//            statement.setString(1, username);
//            statement.setString(2, password);
//            ResultSet resultSet = statement.executeQuery();
//                if (resultSet.next()) {
//                    Account account=new Account
//                            (resultSet.getString("username"),
//                                    resultSet.getString("password"),
//                                    resultSet.getString("email"),
//                                    resultSet.getLong("phoneNumber"),
//                            resultSet.getDouble("balance")
//                            );
//                    return account;
////                    String email = resultSet.getString("email");
////                    Long phoneNumber = resultSet.getLong("phoneNumber");
////                    double balance = resultSet.getDouble("balance");
//                    //return new Account(username, password, email, phoneNumber, balance);
//                } else {
//                    throw new InvalidCredentialsException("Invalid username or password");
//                }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new InvalidCredentialsException("Error authenticating user");
//        }
//    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        String query = "SELECT * FROM accounts";
        try {
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));
            connection.setAutoCommit(false);
                PreparedStatement statement = connection.prepareStatement(query);
     //           statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    long phoneNumber = resultSet.getLong("phoneNumber");
                    double balance = resultSet.getDouble("balance");

                    // Create Account object and add it to the list
                    Account account = new Account(username, password, email, phoneNumber, balance);
                    accounts.add(account);
                }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
//    } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
        return accounts;
        }
//    } catch (SQLException e) {
//            e.printStackTrace();
//        }

        @Override
    public List<Transaction> getTransactionByUsername(String sender) throws TransactionNotFoundException,AccountNotFoundException {
           // String query = "SELECT * FROM accounts WHERE username = ?";
            List<Transaction> transactions = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));
                connection.setAutoCommit(false);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions WHERE sender_username = ?");
                statement.setString(1, sender);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                            sender=resultSet.getString("sender_username");
                            String receiver=resultSet.getString("receiver_username");
                            double amount=resultSet.getDouble("amount");
                            LocalDateTime transaction_date=resultSet.getTimestamp("transaction_date").toLocalDateTime();
                    Transaction transaction = new Transaction(sender, receiver,amount, transaction_date);
                   transactions.add(transaction);
                   return transactions;
                }


            } catch (SQLException transactionNotFound) {
                transactionNotFound.printStackTrace();
            }
            return null;
        }

    public void updateAccountBalance(Account account)throws IOException,AccountNotFoundException {
        String query = "UPDATE accounts SET balance = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, account.getBalance());
            statement.setString(2, account.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean authenticate(String username, String password) throws InvalidCredentialsException, SQLException {

        Connection connection;
        connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.password"));
        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query) ;
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Account account=new Account
                        (resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getLong("phoneNumber"),
                                resultSet.getDouble("balance")
                        );
                return true;
//                    String email = resultSet.getString("email");
//                    Long phoneNumber = resultSet.getLong("phoneNumber");
//                    double balance = resultSet.getDouble("balance");
                //return new Account(username, password, email, phoneNumber, balance);
            } else {
               int attempt=3;
               while(attempt!=0) {
                   throw new InvalidCredentialsException("Invalid username or password");
                   attempt--;
               }
                System.out.println("Account blocked!!");
               System.exit(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidCredentialsException("Error authenticating user");
        }
    }

    public void transferFunds(String senderUsername, String receiverUsername, double amount) {
          //  Connection connection = null;
            try {
                connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));
                connection.setAutoCommit(false); // Start transaction

                // Update sender's balance
                String updateSenderQuery = "UPDATE accounts SET balance = balance - ? WHERE username = ?";
                PreparedStatement updateSenderStatement = connection.prepareStatement(updateSenderQuery);
                updateSenderStatement.setDouble(1, amount);
                updateSenderStatement.setString(2, senderUsername);
                int rowsUpdatedSender = updateSenderStatement.executeUpdate();

                // Update receiver's balance
                String updateReceiverQuery = "UPDATE accounts SET balance = balance + ? WHERE username = ?";
                PreparedStatement updateReceiverStatement = connection.prepareStatement(updateReceiverQuery);
                updateReceiverStatement.setDouble(1, amount);
                updateReceiverStatement.setString(2, receiverUsername);
                int rowsUpdatedReceiver = updateReceiverStatement.executeUpdate();

                // Check if both updates were successful
                if (rowsUpdatedSender == 1 && rowsUpdatedReceiver == 1) {
                    connection.commit();
                    String insertTransactionQuery = "INSERT INTO transactions (sender_username, receiver_username, amount, transaction_date) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertTransactionStatement = connection.prepareStatement(insertTransactionQuery);
                    insertTransactionStatement.setString(1, senderUsername);
                    insertTransactionStatement.setString(2, receiverUsername);
                    insertTransactionStatement.setDouble(3, amount);
                    insertTransactionStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                    insertTransactionStatement.executeUpdate();

                    //System.out.println("Transaction successful");
                } else {
                    connection.rollback();
                    System.out.println("Transaction failed");
                }

            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback(); // Rollback transaction if exception occurs
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true); // Restore auto-commit mode
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transaction = new ArrayList<>();

        String query = "SELECT * FROM transactions";
        try {
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query);
            //           statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String sender = resultSet.getString("sender_username");
                String receiver= resultSet.getString("receiver_username");
                Double amount = resultSet.getDouble("amount");
                LocalDateTime transaction_timestamp = resultSet.getTimestamp("transaction_date").toLocalDateTime();

                Transaction transaction1  = new Transaction(sender,receiver,amount,transaction_timestamp);
               transaction.add(transaction1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//    } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
        return transaction;
    }

    public void addAccount(Account account) {
        try {
          String  query = "insert into accounts(username,password,email,phoneNumber,balance) values(?,?,?,?,?)";
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setLong(4, account.getPhoneNumber());
            statement.setDouble(5, account.getBalance());
            int result = statement.executeUpdate();
            if (result != 0) {
                //logger.log(Level.INFO, resourceBundle.getString("record.push.ok"));

                System.out.println(resourceBundle.getString("record.push.ok"));
            } else {
                //logger.log(Level.INFO, resourceBundle.getString("record.push.fail"));
                System.out.println(resourceBundle.getString("record.push.fail"));
            }

        } catch (SQLException sqlException) {
            System.out.println(resourceBundle.getString("card.not.ok"));
        }


    }
}

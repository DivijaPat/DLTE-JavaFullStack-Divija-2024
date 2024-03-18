package org.example.services;
import org.example.entity.Account;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.remotes.AccountService;
import org.example.remotes.TransactionService;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
        private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        private static final String USERNAME = "system";
        private static final String PASSWORD = "293029";

    public TransactionServiceImpl(FileAccountService fileAccountService) {
    }

    public TransactionServiceImpl() {

    }

    public void transferFunds(String senderUsername, String receiverUsername, double amount) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
                    // Commit transaction if both updates were successful
                    connection.commit();
                    System.out.println("Transaction successful");
                } else {
                    // Rollback transaction if any update failed
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

    }


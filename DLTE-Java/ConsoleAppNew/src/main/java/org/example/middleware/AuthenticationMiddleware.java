//package org.example.middleware;
//
//import oracle.jdbc.OracleDriver;
//import org.example.entity.Account;
//import org.example.exceptions.InvalidCredentialsException;
//import org.example.remotes.UserDetailsRepository;
//
//import javax.security.auth.login.AccountNotFoundException;
//import java.io.*;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AuthenticationMiddleware implements UserDetailsRepository {
//    private final Connection connection;
//
//    public AuthenticationMiddleware() {
//        try {
//            Driver driver=new OracleDriver();
//            DriverManager.registerDriver(driver);
//            connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "293029");
//        } catch (SQLException e) {
//            throw new RuntimeException("Error connecting to the database", e);
//        }
//    }
//
//    public Account authenticate(String username, String password) throws InvalidCredentialsException {
//    String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
//    try (
//         PreparedStatement statement = connection.prepareStatement(query)) {
//        statement.setString(1, username);
//        statement.setString(2, password);
//        try (ResultSet resultSet = statement.executeQuery()) {
//            if (resultSet.next()) {
//                String email = resultSet.getString("email");
//                Long phoneNumber = resultSet.getLong("phoneNumber");
//                double balance = resultSet.getDouble("balance");
//                return new Account(username, password, email, phoneNumber, balance);
//            } else {
//                throw new InvalidCredentialsException("Invalid username or password");
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        throw new InvalidCredentialsException("Error authenticating user");
//    }
//}
//
//    @Override
//    public List<Account> getAllAccounts() {
//        List<Account> accounts = new ArrayList<>();
//        try (
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts")) {
//
//            while (resultSet.next()) {
//                String username = resultSet.getString("username");
//                String password = resultSet.getString("password");
//                String email = resultSet.getString("email");
//                long phoneNumber = resultSet.getLong("phoneNumber");
//                double balance = resultSet.getDouble("balance");
//
//                // Create Account object and add it to the list
//                Account account = new Account(username, password, email, phoneNumber, balance);
//                accounts.add(account);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return accounts;
//    }
//
//    @Override
//    public Account getAccountByUsername(String username) throws AccountNotFoundException {
//        String query = "SELECT * FROM accounts WHERE username = ?";
//        try (
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, username);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new Account(
//                        resultSet.getString("username"),
//                        resultSet.getString("password"),
//                        resultSet.getString("email"),
//                        resultSet.getLong("phoneNumber"),
//                        resultSet.getDouble("balance")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
////    @Override
////    public void updateAccounts(Account accounts) throws IOException {
////        String query = "UPDATE accounts SET balance = ? WHERE username = ?";
////        try (PreparedStatement statement = connection.prepareStatement(query)) {
////            statement.setDouble(1, accounts.getBalance());
////            statement.setString(2, accounts.getUsername());
////            statement.executeUpdate();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
//
//
//    @Override
//    public void updateAccountBalance(String username, double newBalance) throws IOException, AccountNotFoundException {
//        try (PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = ? WHERE username = ?")) {
//            statement.setDouble(1, newBalance);
//            statement.setString(2, username);
//            int result=statement.executeUpdate();
//            if(result>0) System.out.println("updated");
//            else System.out.println("not updated");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

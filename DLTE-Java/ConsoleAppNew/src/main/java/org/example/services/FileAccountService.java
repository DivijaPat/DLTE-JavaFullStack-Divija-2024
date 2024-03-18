//package org.example.services;

//import oracle.jdbc.OracleDriver;
//import org.example.entity.Account;
//import org.example.remotes.UserDetailsRepository;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//public class FileAccountService implements UserDetailsRepository {
//        private final Connection connection;
//
//        public FileAccountService() {
//            try {
//              Driver driver=new OracleDriver();
//              DriverManager.registerDriver(driver);
//              connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "293029");
//            } catch (SQLException e) {
//                throw new RuntimeException("Error connecting to the database", e);
//            }
//        }
//
//        public boolean isEstablished(){
//            return connection!=null;
//        }
//    @Override
//    public void updateAccountBalance(String username, double newBalance) {
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransactionDb", "system", "293029");
//             PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = ? WHERE username = ?")) {
//            statement.setDouble(1, newBalance);
//            statement.setString(2, username);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public List<Account> getAllAccounts() {
//        List<Account> accounts = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TransactionDb", "system", "293029");
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
//        @Override
//        public Account getAccountByUsername(String username) {
//            String query = "SELECT * FROM accounts WHERE username = ?";
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                statement.setString(1, username);
//                ResultSet resultSet = statement.executeQuery();
//                if (resultSet.next()) {
//                    return new Account(
//                            resultSet.getString("username"),
//                            resultSet.getString("password"),
//                            resultSet.getString("email"),
//                            resultSet.getLong("phoneNumber"),
//                            resultSet.getDouble("balance")
//                    );
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//
//    public void updateAccounts(Account account) {
//        String query = "UPDATE accounts SET balance = ? WHERE username = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setDouble(1, account.getBalance());
//            statement.setString(2, account.getUsername());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
 //   }


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


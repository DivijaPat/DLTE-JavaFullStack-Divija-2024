package org.example.Middleware;

import org.example.Entity.UserDetails;
import org.example.Exceptions.UserDetailsException;
import org.example.Remote.UserDetailsRepository;
//import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDetailsDatabaseRepository implements UserDetailsRepository {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("userdetails");
    private Logger logger = Logger.getLogger(UserDetailsDatabaseRepository.class.getName());

    public UserDetailsDatabaseRepository(Connection connection) {
        try{
            this.connection=connection;
            FileHandler fileHandler=new FileHandler("credit-card-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }
    }

    @Override
    public void addUsers() {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO UserDetails(username, password, dob, address, email, phone) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, "annapoornapai");
            statement.setString(2, "anna");
            statement.setDate(3, new java.sql.Date(new Date(2002, 7, 6).getTime()));
            statement.setString(4, "karkala");
            statement.setString(5, "annapoorna@gmail.com");
            statement.setLong(6, 9876543210L);
            statement.executeUpdate();

            // Add other users similarly

            statement.close();
            logger.log(Level.INFO, "Users added successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDetails userDetails) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE UserDetails SET password=?, dob=?, address=?, email=?, phone=? WHERE username=?");
            statement.setString(1, userDetails.getpassword());
            statement.setDate(2, new java.sql.Date(userDetails.getdateOfBirth().getTime()));
            statement.setString(3, userDetails.getaddress());
            statement.setString(4, userDetails.getemailId());
            statement.setLong(5, userDetails.getphoneNumber());
            statement.setString(6, userDetails.getuserName());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                logger.log(Level.WARNING, userDetails.getuserName() + " does not exist in the database.");
                throw new UserDetailsException("User does not exist.");
            } else {
                logger.log(Level.INFO, "Credential updated for " + userDetails.getuserName());
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object verifyPassword(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserDetails WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDetails userDetails = new UserDetails(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getDate("dob"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getLong("phone")
                );
                return userDetails;
            } else {
                logger.log(Level.WARNING, "Username or password incorrect.");
                throw new UserDetailsException("Username or password incorrect.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

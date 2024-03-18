package org.example.middleware;

import oracle.jdbc.driver.OracleDriver;
import org.example.remote.StorageTarget;
import org.example.remote.UserDetailsRepository;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseTarget implements StorageTarget {
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private Connection connection;
    public DatabaseTarget(){
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.password"));
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
    }

    @Override
    public UserDetailsRepository getUserDetailsRepository() {
        return new UserDetailsDatabaseRepository(connection);
    }
}

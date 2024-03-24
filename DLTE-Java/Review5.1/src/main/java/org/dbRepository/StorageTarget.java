package org.dbRepository;

import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ResourceBundle;

public class StorageTarget {
    public static Connection connection;
    static ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    static Logger logger= LoggerFactory.getLogger(StorageTarget.class);
    public static Connection createConnection() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            logger.error(resourceBundle.getString("system.failure"));
            e.printStackTrace();
        }
        return connection;
    }
}

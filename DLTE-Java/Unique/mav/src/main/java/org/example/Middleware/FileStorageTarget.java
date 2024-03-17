package org.example.Middleware;

import org.example.Remote.StorageTarget;
import org.example.Remote.UserDetailsRepository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FileStorageTarget implements StorageTarget {
    @Override
    public UserDetailsRepository getUserDetailsRepository() {
        return new UserDetailsFileRepository("account.txt");
    }
}

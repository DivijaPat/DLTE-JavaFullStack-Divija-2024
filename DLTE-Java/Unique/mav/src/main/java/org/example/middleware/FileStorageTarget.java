package org.example.middleware;

import org.example.middleware.UserDetailsFileRepository;
import org.example.remote.StorageTarget;
import org.example.remote.UserDetailsRepository;

public class FileStorageTarget implements StorageTarget {
    @Override
    public UserDetailsRepository getUserDetailsRepository() {
        return new UserDetailsFileRepository("account.txt");
    }
}

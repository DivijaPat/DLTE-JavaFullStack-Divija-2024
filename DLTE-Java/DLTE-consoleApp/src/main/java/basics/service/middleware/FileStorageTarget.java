package basics.service.middleware;

import basics.service.remotes.StorageTarget;
import basics.service.remotes.UserRepository;

public class FileStorageTarget implements StorageTarget {

    @Override
    public UserRepository getUserRepository() {
        return new UserFileRepository("accountInfo.doc");
    }
}

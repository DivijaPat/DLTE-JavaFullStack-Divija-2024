package basics.service.middleware;

import basics.service.remotes.StorageTarget;

public class FileStorageTarget implements StorageTarget {
    public UserFileRepository getUserRepository(){
        return new UserFileRepository("accountInfo.doc"){

        };
    }
}

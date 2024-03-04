package basics.service.remotes;

import basics.service.middleware.UserFileRepository;

public interface StorageTarget {
 UserFileRepository getUserRepository();
}

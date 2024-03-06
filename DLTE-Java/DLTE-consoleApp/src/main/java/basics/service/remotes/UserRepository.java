package basics.service.remotes;

import basics.service.entity.Accounts;

import java.util.List;

public interface UserRepository {
    List<Accounts> findALL();
    boolean callverifyPassword(String username,String password);
    //void calltransfer(String username,String password,double transferAmount);
    //double balance(String username);
    void addTransactions();
}

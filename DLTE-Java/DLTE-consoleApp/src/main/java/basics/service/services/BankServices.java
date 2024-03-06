package basics.service.services;

import basics.service.entity.Accounts;
import basics.service.remotes.StorageTarget;
import basics.service.remotes.UserRepository;

import java.util.List;

public class BankServices {
    UserRepository userRepository;
    public BankServices(StorageTarget storageTarget) {
        userRepository = storageTarget.getUserRepository();
    }
    public boolean callverifyPassword(String username, String password){
        try{
            return userRepository.callverifyPassword(username,password);
        }
        catch(Exception e){
            return false;
        }
    }

//    public void calltransfer(String username,String password,double transferAmount){
//        try{
//            userRepository.calltransfer(username,password,transferAmount);
//        }
//        catch(Exception e){
//            return;
//        }
//    }
    public void callAddTransactions(){
        try{
            userRepository.addTransactions();
        }catch(Exception e){
            return;
        }
    }
    public List<Accounts> callFindALL(){
        try{
            return userRepository.findALL();
        }catch(Exception e){
            return null;
        }
    }

}

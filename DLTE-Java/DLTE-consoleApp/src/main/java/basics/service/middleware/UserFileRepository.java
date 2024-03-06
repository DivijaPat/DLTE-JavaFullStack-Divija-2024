package basics.service.middleware;

import basics.service.middleware.FileStorageTarget;

import basics.service.entity.Accounts;
import basics.service.remotes.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserFileRepository implements UserRepository {
    private String filePath;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<Accounts> accountList=new ArrayList<>();
    public UserFileRepository(String url){
        filePath=url;
        try{
            FileHandler fileHandler= new FileHandler("accounts-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }
        catch(IOException ioException){}
    }

    private void writeIntoFile(){
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accountList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch(IOException ioException){}
    }

    private void readFromFile(){
        try{
            FileInputStream fileInputStream=new FileInputStream(filePath);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            accountList=(List<Accounts>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch(IOException | ClassNotFoundException ioException){}
    }

    public void addTransaction(){
        readFromFile();
        accountList.add(new Accounts("Divija","3456","Mangalore",8764563547L,"divija@gmail.com",56000D));
        accountList.add(new Accounts("Varun","1234","Bangalore",8767453547L,"Varun@gmail.com",39000D));
        accountList.add(new Accounts("Medhini","2689","Udupi",9964563547L,"Medhini@gmail.com",200000D));
        writeIntoFile();

    }

    public List<Accounts> findALL() {
        readFromFile();
        return accountList;
    }

    @Override
    public boolean callverifyPassword(String username, String password) {
        return false;
    }

    @Override
    public void addTransactions() {

    }

    public boolean verifyPassword(String username,String password){
        readFromFile();
        Accounts accounts=accountList.stream().filter(each->each.getUsername().equals(username)).findFirst().orElse(null);
        if(accounts==null){
            System.out.println("username not found");
            return false;
        }
        else if(!accounts.getPassword().equals(password)){
            System.out.println("Password is incorrect");
            return false;
        }
        else
            return true;
    }

//    public void calltransfer(String username, String password, double transferAmount){
//        readFromFile();
//        if(verifyPassword(username, password)){
//            Accounts accounts=accountList.stream().filter(each->each.getUsername().equals(username)).findFirst().orElse(null);
//            if(accounts.getBalance()< transferAmount){
//                System.out.println("No money in the account exception");
//            }
//            else{
//                accounts.setBalance(accounts.getBalance()-transferAmount);
//                writeIntoFile();
//                System.out.println(balance(username));
//                return;
//            }
//        }
//        else{
//            System.out.println("Password incorrect retry!!");
//        }
//    }


    public double balance(String username){
        readFromFile();
        Accounts accounts=accountList.stream().filter(each->each.getUsername().equals(username)).findFirst().orElse(null);
        return accounts.getBalance();
    }
}

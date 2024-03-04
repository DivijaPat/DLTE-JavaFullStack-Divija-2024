package basics.service.middleware;

import basics.service.entity.Accounts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserFileRepository {
    private String filepath;
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
            FileOutputStream fileOutputStream=new FileOutputStream(filepath);
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
        accountList.add(new Account(1234566,4565,"divija@gmail.com","Divija",35000D))



        writeIntoFile();
    }

    public List<Accounts> findALL() {
        readFromFile();
        return accountList;
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

    public void transfer(String username, String password, double transferAmount){
        readFromFile();
        if(verifyPassword(username, password)){
            Accounts accounts=accountList.stream().filter(each->each.getUsername().equals(username)).findFirst().orElse(null);
            if(accounts.getBalance()< transferAmount){
                System.out.println("No money in the account exception");
            }
            else{
                accounts.setBalance(accounts.getBalance()-transferAmount);
                writeIntoFile();
                System.out.println(balance(username));
                return;
            }
        }
        else{
            System.out.println("Password incorrect retry!!");
        }
    }


    public double balance(String username){
        readFromFile();
        Accounts accounts=accountList.stream().filter(each->each.getUsername().equals(username)).findFirst().orElse(null);
        return accounts.getBalance();
    }
}

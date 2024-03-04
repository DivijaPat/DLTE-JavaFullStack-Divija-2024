package basics.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyBankDatabase <T> implements Activity<T> {
    ArrayList<T> creditCardList=new ArrayList<>();

    public void create(T object)throws IOException{
        creditCardList.add(object);
        writeIntoFile();
    }

    private void writeIntoFile() throws IOException{
        File file= new File("CreditCard.txt");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(creditCardList);
        objectOutputStream.close();
        fileOutputStream.close();

    }
    private void readFromFile()throws IOException,ClassNotFoundException{
        FileInputStream fileInputStream=new FileInputStream("CreditCard.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        creditCardList=(ArrayList<T>)objectInputStream.readObject();
        System.out.println(creditCardList);
        objectInputStream.close();
        fileInputStream.close();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase <CreditCard> myBankDatabase=new MyBankDatabase<>();
        CreditCard creditCard1=new CreditCard(6576883546L,45000D,"Divija",new Date("4/12/2022"),1234);
        myBankDatabase.create(creditCard1);
        myBankDatabase.writeIntoFile();
        myBankDatabase.readFromFile();
    }

}

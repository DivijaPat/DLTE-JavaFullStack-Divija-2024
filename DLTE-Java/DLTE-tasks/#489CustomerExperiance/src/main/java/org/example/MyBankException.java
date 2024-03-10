package org.example;

import java.util.ResourceBundle;

public class MyBankException extends Exception{
    //    private static String message;

    public MyBankException(String message){
        super(ResourceBundle.getBundle("Application").getString("exception.account"));
    }
}
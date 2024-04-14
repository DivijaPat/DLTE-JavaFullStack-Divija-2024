package com.mybank.dao.insurance.exceptions;

public class NoDataFoundException extends Throwable {
    public NoDataFoundException(String message){
        super(message);
    }
}

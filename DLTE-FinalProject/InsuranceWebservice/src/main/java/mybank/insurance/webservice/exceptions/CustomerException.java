package mybank.insurance.webservice.exceptions;

public class CustomerException extends RuntimeException {
    public CustomerException(String message){
        super(message);
    }
}
package mybank.insurance.webservice.exceptions;

public class NoDataFoundException extends Throwable {
    public NoDataFoundException(String message){
        super(message);
    }
}
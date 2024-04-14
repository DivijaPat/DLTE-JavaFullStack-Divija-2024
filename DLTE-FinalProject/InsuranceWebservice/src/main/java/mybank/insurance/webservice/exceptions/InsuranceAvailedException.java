package mybank.insurance.webservice.exceptions;

public class InsuranceAvailedException extends RuntimeException {
    public InsuranceAvailedException(String message) {
        super(message);
    }
}

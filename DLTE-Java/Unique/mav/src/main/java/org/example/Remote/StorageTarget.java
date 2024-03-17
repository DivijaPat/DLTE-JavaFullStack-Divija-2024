package org.example.Remote;

public interface StorageTarget {
    //void transferFunds(String sender, String receiverUsername, double amount) throws InsufficientFundsException, ReceiverNotFoundException, IOException, AccountNotFoundException;
    UserDetailsRepository getUserDetailsRepository();
}


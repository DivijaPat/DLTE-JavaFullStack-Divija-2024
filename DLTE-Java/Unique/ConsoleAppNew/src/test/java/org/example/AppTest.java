package org.example;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.example.entity.Account;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.InvalidCredentialsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.middleware.UserDetailsDatabaseRepository;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


@RunWith(MockitoJUnitRunner.class)
    public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Mock
    private StorageTarget mockStorageTarget;
    @Mock
    private UserDetailsDatabaseRepository mockDatabaseRepository;
    @Mock
    private TransactionServices services;

    @Before
    public void prepareStore() {
        when(mockStorageTarget.getUserDetailsRepository()).thenReturn(mockDatabaseRepository);
        services = new TransactionServices(mockStorageTarget);
    }

    @Test
    public void testVerifyAccount_ValidCredentials_ReturnsTrue() throws InvalidCredentialsException, SQLException {
        String validUserName = "divija";
        String validPassword = "1234";
        when(mockDatabaseRepository.authenticate(validUserName, validPassword)).thenReturn(true);
        boolean result = services.authenticate(validUserName, validPassword);
        assertTrue(result);
       /*
       testing fail test cases
        */
//        when(mockDatabaseRepository.authenticate(validUserName, validPassword)).thenReturn(false);
//      boolean  result = services.authenticate("validUserName", validPassword);
//        assertFalse(result);
    }

    Account senderAccount;
    Account receiverAccount;
//    @Test
//    public void testTransfer() throws InsufficientFundsException, IOException, AccountNotFoundException, ReceiverNotFoundException {
//        senderAccount=new Account("divija","1234","divi@gmail",88743784L,36000D);
//        String sender=senderAccount.getUsername();
//        double balance=senderAccount.getBalance();
//        receiverAccount=new Account("medhini","1234","medhini@gmail",56789567L,44000D);
//        String receiver= receiverAccount.getUsername();
//        double amount=500;
//        when(mockDatabaseRepository.transferFunds(sender, receiver, amount)).thenReturn(balance - amount);
//         double bal=services.transferFunds(sender, receiver, amount);
//        Double expectedBalance = balance - amount;
//        assertEquals(expectedBalance, bal);
//    }
}

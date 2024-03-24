package org.example;

import org.example.entity.Transaction;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.services.TransactionServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    TransactionServices accountService;
    @Mock
    WebServicesDAO webServicesDAO;

    @Mock
    WebServicesDAOService webServicesDAOService;
    @Before
    public void setup(){
        webServicesDAOService.getWebServicesDAOPort();
        webServicesDAOService =new WebServicesDAOService();
    }

    @Test
    public void testAll(){
        TransactionServices accountService = mock(TransactionServices.class);
        Transaction transaction = new Transaction("divija","medhini",1000, LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30));

        // Mock the behavior of callFindAll method of accountService
        List<Transaction> mockTransactionList = (List<Transaction>) new ArrayList();
        mockTransactionList.add(transaction); // Add your mock transaction here
        when(accountService.getAllTransactions()).thenReturn(mockTransactionList);

        // Call the method you want to test
        webServicesDAO.transferFunds("medhini","divija", 1000);
        verify(accountService, times(1)).getAllTransactions();
    }
    Account account;
    @Test
    public void testWithdraw() throws InsufficientFundsException, IOException, AccountNotFoundException, ReceiverNotFoundException {
        account=new Account(123456790L,45679L,"anandi@gmail.com","Anandi",45000,"anandi78","anandi1234");
        String username = "divija";
        String password = "1234";
        double amount = 500;
        double expectedBalance=44500;
        when(accountService.transferFunds(username,password,amount)).thenReturn(expectedBalance);
        double actualBalance = webServicesDAO.transferFunds(username, password, amount);

        // Verify that callWithdraw method of accountService is called exactly once with the correct arguments
        verify(accountService, times(1)).transferFunds(username, password, amount);

        // Verify the returned balance matches the expected balance
        assertEquals(expectedBalance, actualBalance);
    }
}

import First.AccountPost;
import First.FindByUsernameAndDate;
import First.FindByUsernameGet;
import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.exceptions.TransactionNotFoundException;
import org.example.services.TransactionServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppTest{

    @Mock
    private TransactionServices service;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void initiate() throws IOException {
        stringWriter=new StringWriter();
        printWriter=new PrintWriter(stringWriter);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
    }
    @Test
    public void testFindAllByUsername() throws ServletException, IOException, AccountNotFoundException, TransactionNotFoundException {

        FindByUsernameGet findAllByUsername=new  FindByUsernameGet();
        Transaction transaction1=new Transaction("divija","medhini",1000, LocalDateTime.of(2019, 03, 28, 14, 33, 48, 640000));
        Transaction transaction2=new Transaction("medhini","divija",500,LocalDateTime.of(2011, 04, 28, 8, 23, 40, 241000));

        List<Transaction> transactionList= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(httpServletRequest.getParameter("username")).thenReturn("Jayant");
        when(service. getTransactionByUsername(anyString())).thenReturn(transactionList);
        findAllByUsername.doGet(httpServletRequest,httpServletResponse);
        verify(httpServletResponse).setContentType("application/json");
        verify(service).getTransactionByUsername(anyString());
        assertEquals("Expected List","[{\"sender\":\"divija\",\"sender\":\"medhini\",\"amount\":1000\"LocalDateTime\":\"LocalDateTime.of(2011, 04, 28, 8, 23, 40, 241000)\"}",stringWriter.toString().trim());
    }


    @Test
    public void testFindAll() throws ServletException, IOException {
       AccountPost findAll=new AccountPost();
        Transaction transaction1=new Transaction("divija","medhini",1000, LocalDateTime.of(2019, 03, 28, 14, 33, 48, 640000));
        Transaction transaction2=new Transaction("medhini","divija",500,LocalDateTime.of(2011, 04, 28, 8, 23, 40, 241000));

        List<Transaction> transactionList= Stream.of(transaction1,transaction2).collect(Collectors.toList());

        Gson gson=new Gson();
        String transaction=gson.toJson(transactionList);
        System.out.println(transaction);
        when(service.getAllTransactions()).thenReturn(transactionList);
        findAll.doGet(httpServletRequest,httpServletResponse);
        verify(httpServletResponse).setContentType("application/json");
        verify(service).getAllTransactions();
        assertEquals("Expected List","[{\"sender\":\"divija\",\"sender\":\"medhini\",\"amount\":1000\"LocalDateTime\":\"LocalDateTime.of(2011, 04, 28, 8, 23, 40, 241000)\"}",stringWriter.toString().trim());
    }

    @Test
    public void testFindByDateAndUsername() throws ServletException, IOException {
        FindByUsernameAndDate findByDateAndUsername = new FindByUsernameAndDate();
        Transaction transaction1=new Transaction("divija","medhini",1000, LocalDateTime.of(2019, 03, 28, 14, 33, 48, 640000));
        Transaction transaction2=new Transaction("medhini","divija",500,LocalDateTime.of(2011, 04, 28, 8, 23, 40, 241000));
        List<Transaction> transactionList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(httpServletRequest.getParameter("username")).thenReturn("divija");
        when(httpServletRequest.getParameter("date")).thenReturn("28/03/2019");
        findByDateAndUsername.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletResponse).setContentType("application/json");
        assertEquals("Expected List","[{\"sender\":\"divija\",\"sender\":\"medhini\",\"amount\":1000\"LocalDateTime\":\"LocalDateTime.of(2011, 04, 28, 8, 23, 40, 241000)\"}",stringWriter.toString().trim());
    }
}
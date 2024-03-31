package task.jdbctemplate.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import task.jdbctemplate.demo.entity.Transaction;
import task.jdbctemplate.demo.services.TransactionServices;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class JdbctemplateApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionServices transactionServices;

    @Test
    void testSpringContextLoads() {
        assertNotNull(transactionServices, "Spring context not loaded properly");
    }

    @Test
    void testNewTransaction() {
        Transaction transaction1 = new Transaction(3456L,Date.valueOf("2024-02-25"),"Ann","Anusha", 500, "Friend");
        Transaction transaction2=new Transaction(3457L,Date.valueOf("2024-12-15"),"Ridhi","Divija",100,"Family");
        when(jdbcTemplate.update(
                eq("insert into transactions_table values(?,?,?,?,?,?)"),
                any(Long.class),
                any(Date.class),
                any(String.class),
                any(String.class),
                any(Integer.class),
                any(String.class)
        )).thenReturn(1);
        Transaction transactionActual = transactionServices.apiSave(transaction1);
        System.out.println(transactionActual.getTransactionBy());
        assertEquals(transaction1.getTransactionBy(),transactionActual.getTransactionBy());
        assertEquals(transaction2.getTransactionBy(),transactionActual.getTransactionBy());//fails


    }

    @Test
    void testFindBySender(){
        Transaction transaction1 = new Transaction(123456L,Date.valueOf("2023-02-06"), "Anusha", "Ann", 500, "Friend");
        Transaction transaction2 = new Transaction(123457L,Date.valueOf("2024-03-10"), "Divija", "Deeksha", 250, "Friend");
        Transaction transaction3 = new Transaction(123458L,Date.valueOf("2023-12-25"), "Sunidhi", "Hospital", 1000, "Emergency");
        List<Transaction> expected= Stream.of(transaction1,transaction2,transaction3).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindBySender("Divija");
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);
        assertNull(expected);//fails
    }

    @Test
    void testFindByReceiver(){
        Transaction transaction1=new Transaction(123456L,Date.valueOf("2023-02-06"),"Divija","Sandeep",400,"Family");
        Transaction transaction2=new Transaction(123457L,Date.valueOf("2023-02-06"),"Medhini","Divija",250,"Friend");
        Transaction transaction3=new Transaction(123458L,Date.valueOf("2023-02-06"),"Dishitha","Hospital",1000,"Emergency");
        List<Transaction> expected= Stream.of(transaction1).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindByReceiver("Sandeep");
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);
        assertNotNull(expected);
        assertNotNull(actual);//fails as actual is not null
    }

    @Test
    void testFindByAmount(){
        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Divija","Sandeep",400,"Family");
        Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Sunidhi","Divija",400,"Friend");
        Transaction transaction3=new Transaction(123458L,Date.valueOf("2024-02-03"),"Dishitha","Hospital",1000,"Emergency");
        List<Transaction> expected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindByAmount(400);
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);//fails as both are same
    }
}
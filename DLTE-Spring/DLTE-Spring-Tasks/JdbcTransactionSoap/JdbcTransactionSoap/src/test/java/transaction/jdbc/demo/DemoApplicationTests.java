package transaction.jdbc.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import transaction.jdbc.demo.entity.Transaction;
import transaction.jdbc.demo.services.TransactionServices;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DemoApplicationTests {
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
        Transaction transaction1 = new Transaction(3456L,"Ann","Anusha", 500, "Friend",new Date(2023,04,03));
        Transaction transaction2=new Transaction(3457L,"Ridhi","Divija",100,"Family",new Date(2024,04,01));
        when(jdbcTemplate.update(
                eq("insert into transactions_table values(?,?,?,?,?,?)"),
                any(Long.class),
                any(String.class),
                any(String.class),
                any(Integer.class),
                any(String.class),
                any(Date.class)

                )).thenReturn(1);
        Transaction transactionActual = transactionServices.apiSave(transaction1);
        System.out.println(transactionActual.getTransactionBy());
        assertEquals(transaction1.getTransactionBy(),transactionActual.getTransactionBy());
        assertEquals(transaction2.getTransactionBy(),transactionActual.getTransactionBy());


    }

    @Test
    void testFindBySender() {
        Transaction transaction1 = new Transaction(123456L, "Anusha", "Ann", 500, "Friend", new Date(2020, 03, 12));
        Transaction transaction2 = new Transaction(123457L, "Divija", "Deeksha", 250, "Friend", new Date(2019, 04, 02));
        Transaction transaction3 = new Transaction(123458L, "Sunidhi", "Hospital", 1000, "Emergency", new Date(2025, 03, 07));
        List<Transaction> expected = Stream.of(transaction1, transaction2, transaction3).collect(Collectors.toList());
        List<Transaction> notExpected = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual = transactionServices.apiFindBySender("Divija");
        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);
        assertNull(expected);
    }
    @Test
    void testFindByReceiver(){
        Transaction transaction1=new Transaction(123456L,"Divija","Spandana",400,"Family",new Date(2021,04,06));
        Transaction transaction2=new Transaction(123457L,"Medhini","Divija",250,"Friend",new Date(2026,06,11));
        Transaction transaction3=new Transaction(123458L,"Dishitha","Hospital",1000,"Emergency",new Date(2022,04,11));
        List<Transaction> expected= Stream.of(transaction1).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindByReceiver("Spandana");
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);
        assertNotNull(expected);
        assertNotNull(actual);
    }

    @Test
    void testFindByAmount(){
        Transaction transaction1=new Transaction(123456L,"Divija","Bhargavi",400,"Family",new Date(2022,05,05));
        Transaction transaction2=new Transaction(123457L,"Divija","Aru",400,"Friend",new Date(2023,06,06));
       // Transaction transaction3=new Transaction(123458L,"Divija","Hospital",2000,"Emergency",new Date(2023,06,04));
        List<Transaction> expected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        List<Transaction> notExpected= Stream.of(transaction1).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transaction> actual=transactionServices.apiFindByAmount(400);
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);
    }
}
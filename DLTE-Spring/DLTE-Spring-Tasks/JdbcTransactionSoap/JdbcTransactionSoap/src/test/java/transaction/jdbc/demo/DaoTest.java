package transaction.jdbc.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import transaction.jdbc.demo.configs.SoapPhase;
import transaction.jdbc.demo.entity.Transaction;
import transaction.jdbc.demo.services.TransactionServices;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DaoTest {
    @MockBean
    TransactionServices transactionServices;
    @InjectMocks
    SoapPhase soapPhase;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testBySender(){
        Transaction transactions1=new Transaction(123456L,"Divija","Dishitha",500,"Family",new Date(2024,03,31));
        Transaction transactions2=new Transaction(123457L,"Divija","Eeksha",700,"Friend",new Date(2024,03,31));

        List<Transaction> transactionsList= Stream.of(transactions1,transactions2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        List<Transaction> result=transactionServices.apiFindBySender("Divija");
        assertNotNull(result);
        assertEquals(transactionsList,result);
    }
}
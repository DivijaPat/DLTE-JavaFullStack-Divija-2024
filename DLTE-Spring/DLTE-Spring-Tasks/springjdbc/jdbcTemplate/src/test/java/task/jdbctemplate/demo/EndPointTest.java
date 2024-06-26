//package task.jdbctemplate.demo;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import task.jdbctemplate.demo.controller.TransactionController;
//import task.jdbctemplate.demo.entity.Transaction;
//import task.jdbctemplate.demo.services.TransactionServices;
//
//import java.sql.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//public class EndPointTest {
//    @MockBean
//    private TransactionServices transactionServices;
//    @InjectMocks
//    private TransactionController transactionController;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void testSave() throws Exception {
//        String request="{\n"+
//                "  \"transactionId\":123470,\n"+
//                " \"transactionDate\":\"2024-02-25\",\n"+
//                " \"transactionBy\":\"Divija\",\n"+
//                " \"transactionTo\":\"NGO\",\n"+
//                " \"transactionAmount\":2000,\n"+
//                " \"transactionRemarks\":\"General\"\n "+
//                "}";
//        Transaction transaction=new Transaction(123470L,Date.valueOf("2024-02-25"),"Divija","NGO",2000,"General");
//        when(transactionServices.apiSave(any())).thenReturn(transaction);
//
//        mockMvc.perform(post("/transaction/add").contentType(MediaType.APPLICATION_JSON).content(request)).andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(transaction.getTransactionId())).
//                andExpect(jsonPath("$.transactionDate",is(equalTo(transaction.getTransactionDate().toString())))).
//                andExpect(jsonPath("$.transactionBy").value(transaction.getTransactionBy())).
//                andExpect(jsonPath("$.transactionTo").value(transaction.getTransactionTo())).
//                andExpect(jsonPath("$.transactionAmount").value(transaction.getTransactionAmount())).
//                andExpect(jsonPath("$.transactionRemarks").value(transaction.getTransactionRemarks()));
//
//    }
//
//    @Test
//    void testFindBySender() throws Exception {
//        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Divija","Meghana",400,"Family");
//        Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Divija","Arundhathi",400,"Friend");
//        Transaction transaction3=new Transaction(123458L,Date.valueOf("2024-02-03"),"Meghana","Hospital",1000,"Emergency");
//        List<Transaction> expected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        List<Transaction> unExpected=Stream.of(transaction3).collect(Collectors.toList());
//        when(transactionServices.apiFindBySender("Divija")).thenReturn(expected);
//        mockMvc.perform(get("/transaction/send/Divija")).andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(transaction1.getTransactionId())).
//                andExpect(jsonPath("$[0].transactionDate",is(equalTo(transaction1.getTransactionDate().toString())))).
//                andExpect(jsonPath("$[0].transactionBy").value(transaction1.getTransactionBy())).
//                andExpect(jsonPath("$[0].transactionTo").value(transaction1.getTransactionTo())).
//                andExpect(jsonPath("$[0].transactionAmount").value(transaction1.getTransactionAmount())).
//                andExpect(jsonPath("$[0].transactionRemarks").value(transaction1.getTransactionRemarks())).
//                andExpect(jsonPath("$[1].transactionId").value(transaction2.getTransactionId())).
//                andExpect(jsonPath("$[1].transactionDate",is(equalTo(transaction2.getTransactionDate().toString())))).
//                andExpect(jsonPath("$[1].transactionBy").value(transaction2.getTransactionBy())).
//                andExpect(jsonPath("$[1].transactionTo").value(transaction2.getTransactionTo())).
//                andExpect(jsonPath("$[1].transactionAmount").value(transaction2.getTransactionAmount())).
//                andExpect(jsonPath("$[1].transactionRemarks").value(transaction2.getTransactionRemarks()));
//        when(transactionServices.apiFindBySender("Vandana")).thenReturn(unExpected);
//        mockMvc.perform(get("/transaction/send/Vandana")).andExpect(status().isBadRequest());//test case fails
//    }
//
//    @Test
//    void tesFindByReceiver() throws Exception {
//        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Dishitha","Meghana",400,"Family");
//        Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Dishitha","Dishitha",250,"Friend");
//        Transaction transaction3=new Transaction(123458L,Date.valueOf("2024-02-03"),"Dishitha","Hospital",1000,"Emergency");
//        List<Transaction> expected= Stream.of(transaction1).collect(Collectors.toList());
//        when(transactionServices.apiFindByReceiver("Meghana")).thenReturn(expected);
//        mockMvc.perform(get("/transaction/receive/Meghana")).andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(transaction1.getTransactionId())).
//                andExpect(jsonPath("$[0].transactionDate",is(equalTo(transaction1.getTransactionDate().toString())))).
//                andExpect(jsonPath("$[0].transactionBy").value(transaction1.getTransactionBy())).
//                andExpect(jsonPath("$[0].transactionTo").value(transaction1.getTransactionTo())).
//                andExpect(jsonPath("$[0].transactionAmount").value(transaction1.getTransactionAmount())).
//                andExpect(jsonPath("$[0].transactionRemarks").value(transaction1.getTransactionRemarks()));
//    }
//
//    @Test
//    void testFindByAmount() throws Exception {
//        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Divija","Keerthana",400,"Family");
//        Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Divija","Meghana",400,"Friend");
//        Transaction transaction3=new Transaction(123458L,Date.valueOf("2024-02-03"),"Divija","Hospital",1000,"Emergency");
//        List<Transaction> expected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        when(transactionServices.apiFindByAmount(400)).thenReturn(expected);
//        mockMvc.perform(get("/transaction/amount/400")).andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(transaction1.getTransactionId())).
//                andExpect(jsonPath("$[0].transactionDate",is(equalTo(transaction1.getTransactionDate().toString())))).
//                andExpect(jsonPath("$[0].transactionBy").value(transaction1.getTransactionBy())).
//                andExpect(jsonPath("$[0].transactionTo").value(transaction1.getTransactionTo())).
//                andExpect(jsonPath("$[0].transactionAmount").value(transaction1.getTransactionAmount())).
//                andExpect(jsonPath("$[0].transactionRemarks").value(transaction1.getTransactionRemarks())).
//                andExpect(jsonPath("$[1].transactionId").value(transaction2.getTransactionId())).
//                andExpect(jsonPath("$[1].transactionDate",is(equalTo(transaction2.getTransactionDate().toString())))).
//                andExpect(jsonPath("$[1].transactionBy").value(transaction2.getTransactionBy())).
//                andExpect(jsonPath("$[1].transactionTo").value(transaction2.getTransactionTo())).
//                andExpect(jsonPath("$[1].transactionAmount").value(transaction2.getTransactionAmount())).
//                andExpect(jsonPath("$[1].transactionRemarks").value(transaction2.getTransactionRemarks()));
//    }
//
//}
package task.bootdao.springdao.controller;

import com.sun.jmx.mbeanserver.NamedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.bootdao.springdao.entity.Transaction;
import task.bootdao.springdao.services.TransactionServices;


import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;
    /*
    To add new transaction
    http://localhost:8085/transaction/add
    {
"transactionId":123460,
"transactionDate":"2024-02-25",
"transactionBy":"Vinitha",
"transactionTo":"NGO",
"transactionAmount":2000,
"transactionRemarks":"General"
}
     */
    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction){
        Transaction transaction1=transactionServices.apiSave(transaction);
        return transaction1;
    }
    /*
    To find by sender
     [
    {
        "transactionId": 123456,
        "transactionDate": "2024-03-12T00:00:00.000+00:00",
        "transactionBy": "Eeksha",
        "transactionTo": "Divija",
        "transactionAmount": 300,
        "transactionRemarks": "Friend"
    },
    {
        "transactionId": 123457,
        "transactionDate": "2024-01-15T00:00:00.000+00:00",
        "transactionBy": "Eeksha",
        "transactionTo": "Spandana",
        "transactionAmount": 500,
        "transactionRemarks": "Family"
    }
]
     */
    @GetMapping("/send/{sender}")
    public List<Transaction> findBySender(@PathVariable("sender") String sender){
        return transactionServices.apiFindBySender(sender);
    }
    /*
    To find receiver
    [
        {
            "transactionId": 123457,
            "transactionDate": "2024-01-15T00:00:00.000+00:00",
            "transactionBy": "Eeksha",
            "transactionTo": "Spandana",
            "transactionAmount": 500,
            "transactionRemarks": "Family"
        }
    ]
     */
    @GetMapping("/receive/{receiver}")
    public List<Transaction> findByReceiver(@PathVariable("receiver") String receiver){
        return transactionServices.apiFindByReceiver(receiver);
    }
    /*
    [
        {
            "transactionId": 123458,
            "transactionDate": "2024-02-20T00:00:00.000+00:00",
            "transactionBy": "Spandana",
            "transactionTo": "Hotel Jaya",
            "transactionAmount": 250,
            "transactionRemarks": "Bills"
        }
    ]
     */
    @GetMapping("/amount/{amount}")
    public List<Transaction> findBySender(@PathVariable("amount") Integer amount){
        return transactionServices.apiFindByAmount(amount);
    }

}
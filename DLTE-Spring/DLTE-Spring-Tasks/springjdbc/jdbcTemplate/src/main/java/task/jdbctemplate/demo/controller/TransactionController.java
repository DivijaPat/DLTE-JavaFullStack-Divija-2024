package task.jdbctemplate.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.*;
import task.jdbctemplate.demo.entity.Transaction;
import task.jdbctemplate.demo.services.TransactionServices;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;

    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction){
        Transaction transaction1=null;
        try{
            transaction1=transactionServices.apiSave(transaction);
        }catch(TransactionException transactionException){
            System.out.println(transactionException.toString());

        }
        return transaction1;
    }

    @GetMapping("/send/{sender}")
    public List<Transaction> findBySender(@PathVariable("sender") String sender){
        return transactionServices.apiFindBySender(sender);
    }

    @GetMapping("/receive/{receiver}")
    public List<Transaction> findByReceiver(@PathVariable("receiver") String receiver){
        return transactionServices.apiFindByReceiver(receiver);
    }

    @GetMapping("/amount/{amount}")
    public List<Transaction> findBySender(@PathVariable("amount") Integer amount){
        return transactionServices.apiFindByAmount(amount);
    }

}
package com.completewebapp.demo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transact")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    Logger logger= LoggerFactory.getLogger(TransactionController.class);
    @PostMapping("/new")
    public TransactionEntity saved(@RequestBody TransactionEntity transactionEntity){
        try {
            return transactionService.newTransaction(transactionEntity);
        } catch (TransactionException exception) {
            logger.error("Error occurred while saving transaction: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
    @GetMapping("/viewBySender/{sender}")
    public List<TransactionEntity> fetchAllBySender(@PathVariable("sender") String sender){
        try {
            return transactionService.findBySender(sender);
        } catch (TransactionException exception) {
            logger.error("Error occurred while fetching transactions by sender: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
    @GetMapping("/viewByReceiver/{receiver}")
    public List<TransactionEntity> fetchAllByReceiver(@PathVariable("receiver") String receiver){
        try {
            return transactionService.findByReceiver(receiver);
        } catch (TransactionException exception) {
            logger.error("Error occurred while fetching transactions by receiver: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
    @GetMapping("/viewByAmount/{amount}")
    public List<TransactionEntity> fetchALLByAmount(@PathVariable("amount") Double amount){
        try {
            return transactionService.findByAmount(amount);
        } catch (TransactionException exception) {
            logger.error("Error occurred while fetching transactions by amount: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
    @PutMapping("/updateRemarks")
    public TransactionEntity updateTransaction(@RequestBody TransactionEntity transaction){
        TransactionEntity transaction1=transactionService.updateRemarks(transaction);
        return transaction1;
    }

    @DeleteMapping("/deleteBasedOnRangeOfDate/{startDate}/{endDate}")
    public String deleteTransaction(@PathVariable("startDate") String startDateString,@PathVariable("endDate") String endDateString) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=simpleDateFormat.parse(startDateString);
        Date endDate=simpleDateFormat.parse(endDateString);
        return transactionService.removeTransactionBetweenDates(startDate,endDate);

    }
}

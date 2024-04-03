package transaction.jdbc.demo.configs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;
import transaction.jdbc.demo.entity.Transaction;
import transaction.jdbc.demo.services.TransactionServices;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class SoapPhase {

    private final String url = "http://transaction.services";

    @Autowired
    private TransactionServices transactionServices;
    //@PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "newTransactionRequest")
    @ResponsePayload
    public NewTransactionResponse newTransaction(@RequestPayload NewTransactionRequest request) {
        NewTransactionResponse response = new NewTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transaction.Transaction actual= request.getTransaction();
        Transaction daoTransaction = new Transaction();
        BeanUtils.copyProperties(actual,daoTransaction);
        daoTransaction = transactionServices.apiSave(daoTransaction);
        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransaction,actual);
            response.setTransaction(actual);
            serviceStatus.setMessage(actual.getTransactionId()+" has inserted");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId()+" hasn't inserted");
        }
        response.setServiceStatus(serviceStatus);

        return response;
    }

   // @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "findBySenderRequest")
    @ResponsePayload
    public FilterBySenderResponse filterBySender(@RequestPayload FilterBySenderRequest request){
        FilterBySenderResponse response=new FilterBySenderResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.apiFindBySender(request.getSender());
        Iterator<Transaction> iterator =daoTransaction.iterator();
        while (iterator.hasNext()){
            services.transaction.Transaction currentTransaction=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by sender "+request.getSender()+" is fetched");

        response.setServiceStatus(serviceStatus);
        response.getTransaction().addAll(transactions);
        return response;

    }
  //  @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "findByReceiverRequest")
    @ResponsePayload
    public FilterByReceiverResponse filterByReceiver(@RequestPayload FilterByReceiverRequest request){
        FilterByReceiverResponse response=new FilterByReceiverResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.apiFindByReceiver(request.getReceiver());
        Iterator<Transaction> iterator =daoTransaction.iterator();

        while (iterator.hasNext()){
            services.transaction.Transaction currentTransaction=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by receiver "+request.getReceiver()+" is fetched");

        response.setServiceStatus(serviceStatus);
        response.getTransaction().addAll(transactions);
        return response;

    }
   // @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "findByAmountRequest")
    @ResponsePayload
    public FilterByAmountResponse filterByAmount(@RequestPayload FilterByAmountRequest request){
        FilterByAmountResponse response=new FilterByAmountResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.apiFindByAmount(request.getAmount());
        Iterator<Transaction> iterator =daoTransaction.iterator();
        while (iterator.hasNext()){
            services.transaction.Transaction currentTransaction=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by amount "+request.getAmount()+" is fetched");
        response.setServiceStatus(serviceStatus);
        response.getTransaction().addAll(transactions);
        return response;
    }


  //  @PreAuthorize("hasAnyAuthority('admin','manager')")
    @PayloadRoot(namespace = url, localPart = "UpdateRemarksRequest")
    @ResponsePayload
    public UpdateByRemarksResponse updateByRemarks(@RequestPayload UpdateByRemarksRequest request){
        UpdateByRemarksResponse response=new UpdateByRemarksResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transaction.Transaction transaction=new services.transaction.Transaction();

        Transaction daoTransaction=new Transaction();
        BeanUtils.copyProperties(request.getTransaction(),daoTransaction);
        daoTransaction=transactionServices.updateTransaction(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("Transaction updated");
        }else
        {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Transaction update failed");
        }

        BeanUtils.copyProperties(daoTransaction,transaction);
        response.setServiceStatus(serviceStatus);
        response.setTransaction(transaction);
        return response;
    }

   // @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "RemoveTransactionBetweenDatesRequest")
    @ResponsePayload
    public DeleteByRangeOfDatesResponse deleteBasedOnDates(@RequestPayload DeleteByRangeOfDatesRequest request){
        DeleteByRangeOfDatesResponse response=new DeleteByRangeOfDatesResponse();
        Date startDate=request.getStartDate().toGregorianCalendar().getTime();
        Date endDate=request.getEndDate().toGregorianCalendar().getTime();
        ServiceStatus serviceStatus=new ServiceStatus();
        String deleteTransaction=transactionServices.deleteTransaction(startDate,endDate);
        if(deleteTransaction.contains("deleted")){
            serviceStatus.setStatus("FAILURE");
        }else
            serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage(deleteTransaction);
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
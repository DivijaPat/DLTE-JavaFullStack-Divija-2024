package First;


import com.google.gson.Gson;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
//import org.example.remotes.StorageTarget;
import org.example.services.TransactionServices;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

class Transactions {
    private String sender;
    private String receiver;
    private double amount;
    private LocalDateTime transaction_timestamp;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransaction_timestamp() {
        return transaction_timestamp;
    }

    public void setTransaction_timestamp(LocalDateTime transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }
}
@WebServlet("/createTransaction/")
public class CreateNewTransactionPost extends HttpServlet {
    TransactionServices transactionService;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService= new TransactionServices(storageTarget);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getReader().lines();
        Gson gson =new Gson();
        Transactions transactions=gson.fromJson(req.getReader(), Transactions.class);
        try {
            transactionService.transferFunds(transactions.getSender(),transactions.getReceiver(),transactions.getAmount());
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        } catch (ReceiverNotFoundException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        resp.getWriter().println("Transfer of amount "+transactions.getAmount()+" from "+transactions.getSender()+" to "+transactions.getReceiver());
        System.out.println("Transfer of amount "+transactions.getAmount()+" from "+transactions.getSender()+" to "+transactions.getReceiver());
    }

}
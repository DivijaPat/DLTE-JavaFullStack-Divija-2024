package First;

import com.google.gson.Gson;
import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exceptions.TransactionNotFoundException;
import org.example.middleware.DatabaseTarget;

import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("findByUsername/")
public class FindByUsernameGet extends HttpServlet {
    TransactionServices service;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        service= new TransactionServices(storageTarget);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        resp.setContentType("application/json");
        Transaction transaction= null;
        try {
            transaction = (Transaction) service.getTransactionByUsername(username);
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson=new Gson();
        String transactions=gson.toJson(transaction);
        resp.getWriter().println(transactions);
    }
}
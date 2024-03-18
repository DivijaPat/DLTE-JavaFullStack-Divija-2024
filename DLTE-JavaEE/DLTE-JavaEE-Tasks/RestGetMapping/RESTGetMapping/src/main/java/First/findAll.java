package First;

import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.TransactionServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/transaction/findAll")
class FindAll extends HttpServlet {
    TransactionServices service;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
       service= new TransactionServices(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");   //want result in json format
        List<Transaction> transactionList=service.getAllTransactions();//find all users
        Gson gson=new Gson();
        String transaction=gson.toJson(transactionList);//convert list to json
        resp.getWriter().println(transaction);
    }
}
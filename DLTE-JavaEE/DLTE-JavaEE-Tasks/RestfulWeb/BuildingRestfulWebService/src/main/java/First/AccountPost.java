package First;

import com.google.gson.Gson;
import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createAccount/")
public class AccountPost extends HttpServlet {
    TransactionServices service;


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getReader().lines();
        Gson gson =new Gson();
        Account account = gson.fromJson(req.getReader(),Account.class);
        service.addAccount(account);
        resp.getWriter().println("Account added to table");
    }

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        service= new TransactionServices(storageTarget);
    }
}
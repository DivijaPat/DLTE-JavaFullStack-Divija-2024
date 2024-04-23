package servlets.web;

import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.ReceiverNotFoundException;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("TransferServlet")
public class NewTransaction extends HttpServlet {

    public TransactionServices service;
    public ResourceBundle resourceBundle;
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        service=new TransactionServices(storageTarget);
        resourceBundle=ResourceBundle.getBundle("account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String sender = (String) session.getAttribute("username");
        String receiver = (String) session.getAttribute("username");
        double amount=Double.parseDouble(req.getParameter("amount"));
        double updatedBalance = 0;
        try {
            updatedBalance = service.transferFunds(sender, receiver, amount);
        } catch (InsufficientFundsException insufficient) {
            insufficient.printStackTrace();
        } catch (ReceiverNotFoundException receiverNotFound) {
            receiverNotFound.printStackTrace();
        } catch (AccountNotFoundException noAccount) {
            noAccount.printStackTrace();
        }

        if (updatedBalance >= 0) {
            // redirect to the dashboard with the updated balance
            resp.sendRedirect("dashboard.jsp?balance=" + updatedBalance);
        } else {
            // redirect back to the withdrawal page with an error message
            RequestDispatcher dispatcher = req.getRequestDispatcher("transferamount.jsp");
            req.setAttribute("error", resourceBundle.getString("transaction.failure"));
            dispatcher.forward(req, resp);
        }

    }
}
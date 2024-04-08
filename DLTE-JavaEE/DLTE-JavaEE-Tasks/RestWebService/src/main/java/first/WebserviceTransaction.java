package first;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class WebserviceTransaction extends HttpServlet {

    List<Transaction> transactionList = Stream.of(
            new Transaction( 10000, "divija", "Family"),
            new Transaction( 50000, "Eeksha", "Education"),
            new Transaction(10000, "Akshira", "Bills"),
            new Transaction( 10000, "Sinchana", "Emergency"),
            new Transaction( 20000, "Meghana", "Friends")
    ).collect(Collectors.toList());


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transactions</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transactions</h1>");


        for (Transaction transaction : transactionList) {
            writer.println("<div>");
            writer.println("<p>Amount: " + transaction.getTransactionAmount() + "</p>");
            writer.println("<p>To: " + transaction.getTransactionTo() + "</p>");
            writer.println("<p>Remarks: " + transaction.getRemarks() + "</p>");
            writer.println("</div>");
            writer.println("<br>");
        }

        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.getStatus();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactionList.add(transaction);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transaction Insertion</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transaction Added</h1>");
        writer.println("<p>" + transaction.getTransactionTo() + " has been added to the records.</p>");
        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
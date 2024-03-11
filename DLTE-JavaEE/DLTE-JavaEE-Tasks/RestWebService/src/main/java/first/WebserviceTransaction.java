package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/transaction/*")
    public class WebserviceTransaction extends HttpServlet {
        ArrayList<Transaction> transactionList= (ArrayList<Transaction>) Stream.of(
                new Transaction(5000,"Ann","Friend"),
                new Transaction(580,"Anupama","Family"),
                new Transaction(2811,"Annapoorna","Education"),
                new Transaction(10000,"Water","Bills"),
                new Transaction(1200,"Pratiksha","Friend")).collect(Collectors.toList());


        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String requestMaxAmount=req.getParameter("Max");
            String requestMinAmount=req.getParameter("Min");
            if(requestMaxAmount!=null&&requestMinAmount!=null){
                int max=Integer.parseInt(requestMaxAmount);
                int min=Integer.parseInt(requestMinAmount);
                Gson gson=new Gson();
                resp.setContentType("application/json");
                for (Transaction each:transactionList) {
                    if(each.getTransactionAmount()>min&&each.getTransactionAmount()<max){

                        resp.getWriter().println(gson.toJson(each));
                    }
                }
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            else{
                Gson gson=new Gson();
                resp.setContentType("application/json");
                String json = gson.toJson(transactionList);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println(json);
            }
        }
        // post the data object and add it to the arraylist
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Gson gson=new Gson();
            Transaction transaction=gson.fromJson(req.getReader(),Transaction.class);
            transactionList.add(transaction);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Transaction is done to " + transaction.getTransactionTo());
        }
    }


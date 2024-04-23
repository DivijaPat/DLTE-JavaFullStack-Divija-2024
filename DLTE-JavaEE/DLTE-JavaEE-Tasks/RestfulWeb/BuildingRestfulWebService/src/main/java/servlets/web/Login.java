package servlets.web;

import oracle.jdbc.OracleDriver;
import org.example.middleware.DatabaseTarget;
import org.example.remote.StorageTarget;
import org.example.services.TransactionServices;
import org.omg.IOP.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

@WebServlet("/login")
public class Login extends HttpServlet {
    public TransactionServices service;
    public ResourceBundle resourceBundle;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        service = new TransactionServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            String query = "select * from Acccounts where username=? and password=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                HttpSession session=req.getSession();
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                resp.sendRedirect("dashboard.jsp");
            }
            else{
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            SQLException sqlException = e;
            System.out.println(sqlException);
        }
    }
}
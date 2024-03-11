package first;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Webservice",value="/first/*")

public class Webservice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //taking parameters from url
        String investment=req.getParameter("investment");
        String theReturn=req.getParameter("expectedReturn");
        String period=req.getParameter("period");
        String income=req.getParameter("annualIncome");
        String regime=req.getParameter("regime");
        //checking for SIP
        if(investment!=null&&theReturn!=null&&period!=null) {
            double monthlyInvestment = Double.parseDouble(investment);
            double expectedReturn = Double.parseDouble(theReturn);
            int totalPeriod = Integer.parseInt(period);
            double monthlyInterestRate = expectedReturn/12/100;
            double numberOfMonths = 12 * totalPeriod;
            double totalReturn=monthlyInvestment*((Math.pow((1+monthlyInterestRate),(numberOfMonths))-1)*(1+monthlyInterestRate))/monthlyInterestRate;
            double totalMoneyInvested = numberOfMonths * monthlyInvestment;
            double estimatedReturn =totalReturn-totalMoneyInvested;
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Estimated amount="+estimatedReturn+ "Total return = "+ totalReturn+ "Total investment="+ totalMoneyInvested);
        }
        else {
            String received = findTax(Double.parseDouble(income), regime);
            resp.getWriter().println(received);
        }

    }
    //tax calculation
    public String findTax(Double annualIncome,String regime){
        double tax=0;
        switch(regime) {
            case "old":
                if (annualIncome <= 250000) {
                    System.out.println("No tax required to pay");
                } else if (annualIncome > 250000 & annualIncome <= 500000) {
                    System.out.println(" tax is 5%");
                    tax = annualIncome * 0.05;
                    System.out.println("Tax amount =" + tax);
                } else if (annualIncome > 50000 & annualIncome <= 1000000) {
                    System.out.println("tax is 20%");
                    tax = annualIncome * 0.2;
                    System.out.println("Tax amount =" + tax);
                } else {
                    System.out.println("tax is 30%");
                    tax = annualIncome * 0.3;
                    System.out.println("Tax amount =" + tax);
                }
                break;
            case "new":
                //for income tax based on new tax slab
                if (annualIncome <= 300000) {
                    System.out.println("No tax required to pay");
                } else if (annualIncome >= 300001 & annualIncome <= 600000) {
                    System.out.println("tax is 5%");
                    tax = annualIncome * 0.05;
                    System.out.println("Tax amount =" + tax);
                } else if (annualIncome >= 600001 & annualIncome <= 900000) {
                    System.out.println("tax is 10%");
                    tax = annualIncome * 0.1;
                    System.out.println("Tax amount =" + tax);
                } else if (annualIncome >= 900001 & annualIncome <= 1200000) {
                    System.out.println("tax is 15%");
                    tax = annualIncome * 0.15;
                    System.out.println("Tax amount =" + tax);
                } else if (annualIncome >= 1200001 & annualIncome <= 1500000) {
                    System.out.println("tax is 20%");
                    tax = annualIncome * 0.2;
                    System.out.println("Tax amount =" + tax);
                } else {
                    System.out.println("tax is 30%");
                    tax = annualIncome * 0.3;
                    System.out.println("Tax amount =" + tax);
                }
                break;
        }
        return Double.toString(tax);
    }


}

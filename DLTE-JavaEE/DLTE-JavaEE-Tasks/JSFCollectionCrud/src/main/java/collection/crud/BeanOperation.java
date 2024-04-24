package collection.crud;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class BeanOperation {
    List<Loan> loans;

    public BeanOperation() {
        loans=new ArrayList<>();
        loans.add(new Loan(123,45000,"05/04/2023","closed","Divija",8765467364L));
        loans.add(new Loan(456,67326,"10/01/2023","closed","Eeksha",8345654078L));
        loans.add(new Loan(789,78354,"23/02/2023","open","Aru",9865109367L));
        loans.add(new Loan(101,56000,"13/03/2023","open","Akshira",9082164722L));
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
    public void addLoan(Loan loan){
        loans.add(loan);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Added loans successfully",null));
    }
    public List<Loan> displayClosedLoan(){
        return loans.stream().filter(loan1->loan1.getLoanStatus().equals("closed")).collect(Collectors.toList());
    }
    public void deleteLoan(Long loanNumber){
        loans.removeIf(loan1->loan1.getLoanNumber()==loanNumber);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Loans deleted successfully",null));

    }
}

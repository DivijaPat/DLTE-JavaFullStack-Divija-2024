package basics.service;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    Scanner scanner=new Scanner(System.in);
    public static void main( String[] args ) {
        try {
            App app = new App();
            EmployeeDetails emp = new EmployeeDetails();
            System.out.println(emp.getFirstName().equals("hello"));
        }
        catch(NullPointerException nullPointerException){
            System.out.println("null pointer exception"+ nullPointerException.getStackTrace());
        }

    }

}

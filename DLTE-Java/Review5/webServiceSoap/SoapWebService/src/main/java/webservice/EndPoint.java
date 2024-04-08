package webservice;
import javax.xml.ws.Endpoint;

public class EndPoint {
    public static void main(String[] args) {
        String url = "http://localhost:8081/EmployeeWeb";
        WebServiceImplementation webServiceImplementation=new WebServiceImplementation();
        System.out.println("Employee Web Service is published at: " + url);
        Endpoint.publish(url, webServiceImplementation);
    }
}
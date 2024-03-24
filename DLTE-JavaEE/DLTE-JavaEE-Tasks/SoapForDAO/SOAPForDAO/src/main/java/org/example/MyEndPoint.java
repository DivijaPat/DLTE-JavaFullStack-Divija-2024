package org.example;

import javax.xml.ws.Endpoint;

public class MyEndPoint {
    private static String url="http://localhost:1011/webServiceDAO";

    public static void main(String[] args) {
        WebServiceDAO webServices=new WebServiceDAO();
        System.out.println("Webservice running at "+url);
        Endpoint.publish(url,webServices);
    }
}
package or.soap;

import javax.xml.ws.Endpoint;

public class EndPoint {
    private static String url="http://localhost:6543/demo";
    public static void main(String[] args) {
        MySource mySource=new MySource();
        System.out.println("SOAP webservice running "+url);
        Endpoint.publish(url,mySource);
    }
}

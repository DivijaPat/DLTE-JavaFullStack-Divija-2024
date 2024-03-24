package org.example;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


@WebServiceClient(name = "WebServicesDAOService", targetNamespace = "http://example.org/", wsdlLocation = "http://localhost:1011/webServiceDAO?wsdl")
public class WebServicesDAOService extends Service
{

    private final static URL WEBSERVICESDAOSERVICE_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICESDAOSERVICE_EXCEPTION;
    private final static QName WEBSERVICESDAOSERVICE_QNAME = new QName("http://example.org/", "WebServicesDAOService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:1011/webServiceDAO?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICESDAOSERVICE_WSDL_LOCATION = url;
        WEBSERVICESDAOSERVICE_EXCEPTION = e;
    }

    public WebServicesDAOService() {
        super(__getWsdlLocation(), WEBSERVICESDAOSERVICE_QNAME);
    }

    public WebServicesDAOService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICESDAOSERVICE_QNAME, features);
    }

    public WebServicesDAOService(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICESDAOSERVICE_QNAME);
    }

    public WebServicesDAOService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICESDAOSERVICE_QNAME, features);
    }

    public WebServicesDAOService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServicesDAOService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "WebServicesDAOPort")
    public WebServicesDAO getWebServicesDAOPort() {
        return super.getPort(new QName("http://example.org/", "WebServicesDAOPort"), WebServicesDAO.class);
    }

    @WebEndpoint(name = "WebServicesDAOPort")
    public WebServicesDAO getWebServicesDAOPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://example.org/", "WebServicesDAOPort"), WebServicesDAO.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICESDAOSERVICE_EXCEPTION!= null) {
            throw WEBSERVICESDAOSERVICE_EXCEPTION;
        }
        return WEBSERVICESDAOSERVICE_WSDL_LOCATION;
    }

}
package org.example;

import org.example.entity.Transaction;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import java.util.List;

@WebService(name = "WebServiceDAO", targetNamespace = "http://example.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
        ObjectFactory.class
})
interface WebServicesDAO {

    @WebMethod
    @WebResult(name = "getTransactionByUsername", partName = "getTransactionByUsername")
    @Action(input = "http://example.org/WebServicesDAO/getTransactionByUsername", output = "http://example.org/WebServicesDAO/getTransactionByUsernameResponse")
    public List<Transaction> getTransactionByUsername(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

    @WebMethod
    @WebResult(name = "transferFunds", partName = "transferFunds")
    @Action(input = "http://example.org/WebServicesDAO/transferFundsRequest", output = "http://example.org/WebServicesDAO/transferFundsResponse")
    public double transferFunds(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0,
            @WebParam(name = "arg1", partName = "arg1")
                    String arg1,
            @WebParam(name = "arg2", partName = "arg2")
                    double arg2);

    @WebMethod
    @Action(input = "http://example.org/WebServicesDAO/addAccountRequest", output = "http://example.org/WebServicesDAO/addAccountResponse")
    public void addAccount(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0,
            @WebParam(name = "arg1", partName = "arg1")
                   String arg1,
            @WebParam(name = "arg2", partName = "arg2")
                    String arg2,
            @WebParam(name = "arg3", partName = "arg3")
                    long arg3,
            @WebParam(name = "arg4", partName = "arg4")
                    double arg4);


}

package or.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MySource", targetNamespace = "http://soap.or/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MySource {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://soap.or/MySource/removeMembersRequest", output = "http://soap.or/MySource/removeMembersResponse")
    public String removeMembers(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://soap.or/MySource/addMembersRequest", output = "http://soap.or/MySource/addMembersResponse")
    public String addMembers(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
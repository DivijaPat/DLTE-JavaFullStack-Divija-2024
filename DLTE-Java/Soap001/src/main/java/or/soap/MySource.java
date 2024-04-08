package or.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class MySource {
    List<String> members;
    public MySource(){
        members= Stream.of("divija","Sunidhi","Bhavya","Swathi").collect(Collectors.toList());
    }
    @WebMethod
    public String addMembers(String name){
        members.add(name);
        return name+" has  been added";
    }

    public String removeMembers(String name){
        members.remove(name);
        return name+" has  been removed";
    }
}

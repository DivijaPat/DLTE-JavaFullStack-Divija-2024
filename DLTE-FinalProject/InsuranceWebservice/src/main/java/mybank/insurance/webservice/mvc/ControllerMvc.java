package mybank.insurance.webservice.mvc;
import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web")
public class ControllerMvc {
    @Autowired
    MyBankUsersServices myBankService;

    @GetMapping("/")
    public String landing()
    {
        return "index";
    }

    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String homePage()
    {
        return "dashboard";
    }

    @PostMapping("/")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "index";
    }
    @GetMapping("/view")
    public String view(){
        return "viewInsurance";
    }
    @GetMapping("/viewByCoverage")
    public String filterByCoverage(){
        return "filterByCoverage";
    }
    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}

package mybank.insurance.webservice.mvc;

import com.mybank.dao.insurance.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ResourceBundle;
@Controller
@RequestMapping("/login")
class ControllerClass {
    @Autowired
    MyBankUsersServices myBankService;

    Logger logger= LoggerFactory.getLogger(ControllerClass.class);

    ResourceBundle bundle=ResourceBundle.getBundle("application");

    @GetMapping("/")
    public String landing(){
        return "index";
    }

    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String homePage(){
        return "dashboard";
    }

    @PostMapping("/")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "index";
    }
    }

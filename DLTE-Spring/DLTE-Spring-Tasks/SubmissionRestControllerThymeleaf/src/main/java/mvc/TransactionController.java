package mvc;

import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.TransactionServices;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("transaction",new Transaction());
        return "index";
    }

    @GetMapping("/new")
    public String show(Model model){
        model.addAttribute("transaction",new Transaction());
        return "newTransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@ModelAttribute Transaction transaction, Model model){
        model.addAttribute("transaction",transaction);
        Transaction transaction1=transactionServices.apiSave(transaction);
        if(transaction1!=null){
            model.addAttribute("message","Transaction successful!");
            model.addAttribute("transaction",transaction1);
            return "index";
        }else{
            model.addAttribute("message","Transaction failed!");
            return "newTransaction";
        }
    }

}
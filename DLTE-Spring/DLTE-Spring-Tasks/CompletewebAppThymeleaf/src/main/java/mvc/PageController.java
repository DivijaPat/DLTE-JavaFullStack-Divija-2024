package mvc;

import com.completewebapp.demo.TransactionController;
import com.completewebapp.demo.TransactionEntity;
import com.completewebapp.demo.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/web")
public class PageController {

    @Autowired
    TransactionController transactionService;
    @GetMapping("/")
    public String landing(){
        return "index";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String showIndexPage(Model model) {
        model.addAttribute("transaction", new TransactionEntity());
        return "newTransaction";
    }
    @RequestMapping(value="/dash", method = RequestMethod.GET)
    public String homePage(){
        return "dashboard";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String newTransaction(@Valid @ModelAttribute("transaction") TransactionEntity transactionEntity,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam("transactionDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date transactionDate) {
        if (bindingResult.hasErrors()) {
            return "newTransaction";
        }

        try {
            transactionEntity.setTransactionDate(transactionDate); // Set the parsed date
            transactionService.saved(transactionEntity);
            redirectAttributes.addFlashAttribute("message", "Transaction created successfully");
            return "redirect:/web/confirmation";
        } catch (TransactionException exception) {
            // Handle other exceptions, if any
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            return "error";
        }
    }


    @GetMapping("/findBySender")
    public String viewTransactionsBySender(@RequestParam("sender") String sender, Model model) {
        try {
            List<TransactionEntity> transactions = transactionService.fetchAllBySender(sender);
            model.addAttribute("transactions", transactions);
            return "viewTransaction"; // Return the name of the Thymeleaf template
        } catch (TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/findByReceiver")
    public String viewTransactionsByReceiver(@RequestParam("receiver") String receiver, Model model) {
        try {
            List<TransactionEntity> transactions = transactionService.fetchAllByReceiver(receiver);
            model.addAttribute("transactions", transactions);
            return "viewTransaction";
        } catch (TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/findByAmount")
    public String viewTransactionsByAmount(@RequestParam("amount") Double amount, Model model) {
        try {
            List<TransactionEntity> transactions = transactionService.fetchALLByAmount(amount);
            model.addAttribute("transactions", transactions);
            return "viewTransaction";
        } catch (TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/removeBetweenDates")
    public String removeTransactionsBetweenDates(@RequestParam("startDate") String startDateString,
                                                 @RequestParam("endDate") String endDateString,
                                                 RedirectAttributes redirectAttributes,
                                                 Model model) {
        try {
            String result = transactionService.deleteTransaction(startDateString,endDateString);
            redirectAttributes.addFlashAttribute("message", "Transaction Deleted successfully"+result);
            return "redirect:/web/confirmation";
        } catch (TransactionException | ParseException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }


    @RequestMapping(value = "/confirmation",method = RequestMethod.GET)
    public String transactionConfirmation(Model model) {
        if (model.containsAttribute("message")) {
            String message = (String) model.getAttribute("message");
            model.addAttribute("message", message);
        }
        return "transactionConfirmation";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String handleError(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");

        redirectAttributes.addFlashAttribute("status", statusCode);
        redirectAttributes.addFlashAttribute("error", errorMessage);

        return "redirect:/web/error";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
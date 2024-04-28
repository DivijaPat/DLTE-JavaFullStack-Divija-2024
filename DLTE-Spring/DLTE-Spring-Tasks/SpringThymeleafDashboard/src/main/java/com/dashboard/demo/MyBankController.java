package com.dashboard.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/insurance")
public class MyBankController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String myTemplate(){
        return "dashboard";
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String view() {
        return "view";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout() {
        return "dashboard";
    }

}
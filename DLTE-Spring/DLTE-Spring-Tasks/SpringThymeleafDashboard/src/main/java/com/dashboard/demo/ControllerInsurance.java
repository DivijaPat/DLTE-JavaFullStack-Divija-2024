package com.dashboard.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/insurance")
public class ControllerInsurance {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping(value = "/viewall",method = RequestMethod.GET)
    public String viewAll() {
        return "viewall";
    }

    @RequestMapping(value = "/viewbycoverage",method = RequestMethod.GET)
    public String viewByCoverage() {
        return "viewbycoverage";
    }

}
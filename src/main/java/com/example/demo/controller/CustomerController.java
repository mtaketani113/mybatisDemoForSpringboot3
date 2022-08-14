package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CustomerService;

@Controller
public class CustomerController {
    
    @Autowired CustomerService customerService;
    
    @RequestMapping("/")
    public String searchAllCustomers(Model model) {

        model.addAttribute("customers"
            , customerService.searchAllCustomers());
        
        return "customer";
        
    }
}

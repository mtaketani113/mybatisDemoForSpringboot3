package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/customer/{id}")
    public String searchById(Model model, @PathVariable("id") String id) {

        model.addAttribute("customer"
            , customerService.searchById(id));
        
        return "customerDetail";
        
    }
}

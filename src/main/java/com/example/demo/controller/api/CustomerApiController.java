package com.example.demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerApiController {
    
    @Autowired CustomerService customerService;
    
    @GetMapping("/api/customer")
    public List<Customer> searchById() {
        return customerService.searchAllCustomers();
    }

    @GetMapping("/api/customer/{id}")
    public Customer searchById(@PathVariable("id") String id) {
        return customerService.searchById(id);
    }

    @PutMapping(path = "/api/customer/new")
    public String create(@ModelAttribute Customer customer ) {
        customerService.register(customer);
        return "redirect:/";
    }

    @PostMapping(path = "/api/customer/update")
    public String update(@ModelAttribute Customer customer ) {
        customerService.register(customer);
        return "redirect:/";
    }

    @DeleteMapping("/api/customer/delete/{id:^[0-9]+$}")
    public String delete(@PathVariable("id") String id) {
        customerService.delete(id);
        return "redirect:/";
    }
}

package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

  @Autowired CustomerService customerService;

  @GetMapping("/")
  public String searchAllCustomers(Model model) {

    model.addAttribute("customers", customerService.searchAllCustomers());

    return "customer/customer";
  }

  @GetMapping("/customer/new")
  public String add(Model model) {
    model.addAttribute("customer", new Customer());

    return "customer/customerDetail";
  }

  @PostMapping(path = "/customer/register")
  public String register(Model model, @ModelAttribute Customer customer) {
    customerService.register(customer);
    return "redirect:/";
  }

  @GetMapping("/customer/delete/{id:^[0-9]+$}")
  public String delete(@PathVariable("id") String id) {
    customerService.delete(id);
    return "redirect:/";
  }

  @GetMapping("/customer/{id:^[0-9]+$}")
  public String searchById(Model model, @PathVariable("id") String id) {
    model.addAttribute("customer", customerService.searchById(id));

    return "customer/customerDetail";
  }
}

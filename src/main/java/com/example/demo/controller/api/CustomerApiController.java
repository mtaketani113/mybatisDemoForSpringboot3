package com.example.demo.controller.api;

import com.example.demo.annotation.Authorize;
import com.example.demo.annotation.NonAuthorize;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApiController {

  @Autowired CustomerService customerService;

  @GetMapping("/api/customer")
  @NonAuthorize
  public List<Customer> searchAll() {
    return customerService.searchAllCustomers();
  }

  @GetMapping("/api/customer/{id}")
  @Authorize
  public Customer searchById(@PathVariable("id") String id) {
    return customerService.searchById(id);
  }

  @PutMapping(path = "/api/customer/new")
  @Authorize
  public String create(@RequestBody Customer customer) {
    customerService.register(customer);
    return "{\"result\": \"OK\"}";
  }

  @PostMapping(path = "/api/customer/update")
  @Authorize
  public String update(@RequestBody Customer customer) {
    customerService.register(customer);
    return "{\"result\": \"OK\"}";
  }

  @DeleteMapping("/api/customer/delete/{id:^[0-9]+$}")
  @Authorize
  public String delete(@PathVariable("id") String id) {
    customerService.delete(id);
    return "{\"result\": \"OK\"}";
  }
}

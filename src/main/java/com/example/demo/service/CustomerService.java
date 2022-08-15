package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerMapper;

@Service
@Transactional
public class CustomerService {
    
    @Autowired CustomerMapper customerMapper;

    public List<Customer> searchAllCustomers(){
        return customerMapper.searchAllCustomer();
    }

    public Customer searchById(String id){
        return customerMapper.searchById(id).orElse(new Customer());
    }

}

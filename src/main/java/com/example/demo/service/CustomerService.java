package com.example.demo.service;

import java.util.List;

import org.apache.groovy.parser.antlr4.util.StringUtils;
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

    public void register(Customer customer){

        if(StringUtils.isEmpty(customer.getId())){
            customerMapper.create(customer);
        }else{
            customerMapper.update(customer);
        }
        
    }

    public void delete(String id){
        customerMapper.delete(id);
    }

}

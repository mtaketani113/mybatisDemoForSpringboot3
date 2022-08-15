package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Customer;

@Mapper
public interface CustomerMapper {

    List<Customer> searchAllCustomer();

    Optional<Customer> searchById(String id);
}
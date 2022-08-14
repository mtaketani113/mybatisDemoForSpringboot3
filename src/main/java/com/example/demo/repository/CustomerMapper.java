package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Customer;

@Mapper
public interface CustomerMapper {

    List<Customer> searchAllCustomer();
}
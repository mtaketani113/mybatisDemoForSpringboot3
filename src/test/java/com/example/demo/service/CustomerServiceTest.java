package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Customer;

@SpringBootTest
@Sql("/init.sql")
@Transactional
public class CustomerServiceTest {

    @Autowired private CustomerService customerService;

	@Test
	public void 顧客の追加テスト() {

        Customer customer = new Customer();
        customer.setPost("post");
        customer.setAddress("address");
        customer.setName("name");
        customerService.register(customer);

        List<Customer> results = customerService.searchAllCustomers();

        assertEquals(results.size(), 1);
        Customer result = results.get(0);

        assertNotNull(result.getId());
        assertEquals(result.getPost(), "post");
        assertEquals(result.getAddress(), "address");
        assertEquals(result.getName(), "name");

    }
    
    
}

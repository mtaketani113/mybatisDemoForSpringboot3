package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.model.Customer;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Sql("/init.sql")
@Transactional
public class CustomerServiceTest {

  @Autowired private CustomerService customerService;

  @Test
  public void 顧客の追加テスト() {

    Customer customer = Customer.builder().post("post").address("address").name("name").build();
    customerService.register(customer);

    List<Customer> results = customerService.searchAllCustomers();

    assertEquals(results.size(), 1);
    Customer result = results.get(0);

    assertNotNull(result.getId());
    assertEquals(result.getPost(), "post");
    assertEquals(result.getAddress(), "address");
    assertEquals(result.getName(), "name");
  }

  @Test
  public void 顧客の複数追加テスト() {

    for (int i = 0; i < 10; i++) {
      Customer customer =
          Customer.builder().post("post" + i).address("address" + i).name("name" + i).build();
      customerService.register(customer);
    }

    List<Customer> results = customerService.searchAllCustomers();

    assertEquals(results.size(), 10);

    for (int i = 0; i < 10; i++) {
      Customer result = results.get(i);
      assertNotNull(result.getId());
      assertEquals(result.getPost(), "post" + i);
      assertEquals(result.getAddress(), "address" + i);
      assertEquals(result.getName(), "name" + i);
    }
  }

  @Test
  public void 顧客の更新テスト() {

    Customer customer = Customer.builder().post("post").address("address").name("name").build();
    customerService.register(customer);

    customer = customerService.searchAllCustomers().get(0);

    // 更新
    Customer customerChanged =
        Customer.builder()
            .id(customer.getId())
            .post("changedPost")
            .address("changedAddress")
            .name("changedName")
            .build();
    customerService.register(customerChanged);

    List<Customer> results = customerService.searchAllCustomers();

    assertEquals(results.size(), 1);
    Customer result = results.get(0);

    assertNotNull(result.getId());
    assertEquals(result.getPost(), "changedPost");
    assertEquals(result.getAddress(), "changedAddress");
    assertEquals(result.getName(), "changedName");
  }

  @Test
  public void 顧客の削除テスト() {

    Customer customer = Customer.builder().post("post").address("address").name("name").build();
    customerService.register(customer);

    customer = customerService.searchAllCustomers().get(0);

    // 削除
    customerService.delete(customer.getId());

    List<Customer> results = customerService.searchAllCustomers();

    assertEquals(results.size(), 0);
  }
}

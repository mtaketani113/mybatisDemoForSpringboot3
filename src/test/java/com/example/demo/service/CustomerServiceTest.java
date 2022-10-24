package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.model.Customer;
import java.util.ArrayList;
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

    this.checkCustomer(customer, result);
  }

  @Test
  public void 顧客の複数追加テスト() {

    List<Customer> customers = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Customer customer =
          Customer.builder().post("post" + i).address("address" + i).name("name" + i).build();
      customerService.register(customer);
      customers.add(customer);
    }

    List<Customer> results = customerService.searchAllCustomers();

    assertEquals(results.size(), 10);

    for (int i = 0; i < 10; i++) {
      Customer result = results.get(i);
      this.checkCustomer(customers.get(i), result);
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
    this.checkCustomer(customerChanged, result);
  }

  @Test
  public void 顧客の削除テスト() {

    Customer customer = Customer.builder().post("post").address("address").name("name").build();
    customerService.register(customer);

    List<Customer> results = customerService.searchAllCustomers();
    // 削除前は1件ヒットすることを確認
    assertEquals(results.size(), 1);

    // 削除
    customerService.delete(results.get(0).getId());

    results = customerService.searchAllCustomers();
    // 1件もヒットせず削除されていることを確認
    assertEquals(results.size(), 0);
  }

  /**
   * 顧客情報のチェックメソッド
   *
   * @param expect
   * @param actual
   */
  private void checkCustomer(Customer expect, Customer actual) {
    assertNotNull(actual.getId());
    assertEquals(expect.getPost(), actual.getPost());
    assertEquals(expect.getAddress(), actual.getAddress());
    assertEquals(expect.getName(), actual.getName());
  }
}

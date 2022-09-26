package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer {

  String id;
  String name;
  String post;
  String address;
}

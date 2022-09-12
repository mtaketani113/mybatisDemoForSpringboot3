package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  String id;
  String email;
  Boolean verifiedEmail;
  String name;
  String givenName;
  String familyName;
  String picture;
  String locale;
}

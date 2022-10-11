package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
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

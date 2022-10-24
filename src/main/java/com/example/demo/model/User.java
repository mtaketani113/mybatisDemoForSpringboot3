package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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

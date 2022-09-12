package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class File {
  String id;
  String fileName;
  byte[] fileData;
}

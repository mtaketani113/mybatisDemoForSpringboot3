package com.example.demo.exceptions;

import lombok.Getter;

public class DemoRuntimeException extends RuntimeException {
  @Getter private String errorCode;

  public DemoRuntimeException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public DemoRuntimeException(Throwable cause) {
    super(cause);
  }

  public DemoRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }
}

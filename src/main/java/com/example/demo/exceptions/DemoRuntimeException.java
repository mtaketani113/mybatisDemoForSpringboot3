package com.example.demo.exceptions;

public class DemoRuntimeException extends RuntimeException {
  public DemoRuntimeException() { }
  public DemoRuntimeException(String message) { super(message); }
  public DemoRuntimeException(Throwable cause) { super(cause); }
  public DemoRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }
}
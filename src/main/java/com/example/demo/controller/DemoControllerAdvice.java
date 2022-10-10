package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.exceptions.DemoRuntimeException;
import com.example.demo.response.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class DemoControllerAdvice {

  @ExceptionHandler({DemoRuntimeException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleEmployeeNotFoundException(DemoRuntimeException e) {
    log.error(e.getErrorCode(), e.getMessage());
    return new ErrorResponse(e.getErrorCode(), e.getMessage());
  }
}

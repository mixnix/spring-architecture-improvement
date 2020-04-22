package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionAdvice {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Map<String, String>> personNotFoundHandler(EntityNotFoundException ex) {
    Map<String, String> body = new HashMap<>();
    body.put("message", ex.getMessage());

    return ResponseEntity.status(404).body(body);
  }
}
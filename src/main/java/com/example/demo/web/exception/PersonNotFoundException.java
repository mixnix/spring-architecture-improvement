package com.example.demo.web.exception;

public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException(Long id) {
    super("Could not find person with id: " + id);
  }
}
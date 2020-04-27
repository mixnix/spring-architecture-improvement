package com.example.demo.service;

import com.example.demo.domain.dao.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    Person create(Person person);
    Person getById(Long id);
    Person update(Person person, Long id);
    void deleteById(Long id);
    Person create(String firstName, String lastName);
}

package com.example.demo.persistance.dao;

import com.example.demo.persistance.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

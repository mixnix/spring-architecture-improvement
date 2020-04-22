package com.example.demo.repository;

import com.example.demo.domain.dao.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

package com.example.demo;

import com.example.demo.persistance.dao.PersonRepository;
import com.example.demo.persistance.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Component
public class DatabaseLoader implements CommandLineRunner {

	private final PersonRepository personRepository;

	@Autowired
	public DatabaseLoader(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Transactional
	@Override
	public void run(String... strings) throws Exception {
		this.personRepository.save(new Person(1l, "Michał", "Kichał",
				"male", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
				"123456789"));


	}
}
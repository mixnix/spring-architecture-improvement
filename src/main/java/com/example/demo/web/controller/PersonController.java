package com.example.demo.web.controller;

import com.example.demo.persistance.dao.PersonRepository;
import com.example.demo.persistance.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    private final Logger log = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/persons")
    public Collection<Person> persons() {
        log.info("GET /persons");
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable Long id) {
        log.info("GET /persons/{}", id);
        return personRepository.getOne(id);
    }

    @PutMapping("/persons/{id}")
    public Person updatePerson(@ModelAttribute @Valid Person person, @PathVariable Long id){
        log.info("PUT /persons/{}, received data: {}", id, person);

        Person tempPerson = personRepository.getOne(id);
        tempPerson.setFirstName(person.getFirstName());
        tempPerson.setLastName(person.getLastName());
        tempPerson.setSex(person.getSex());
        tempPerson.setBirtdate(person.getBirtdate());
        tempPerson.setPhoneNumber(person.getPhoneNumber());
        return personRepository.save(tempPerson);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable Long id){
        log.info("DELETE /persons/{}", id);

        personRepository.deleteById(id);
    }
}

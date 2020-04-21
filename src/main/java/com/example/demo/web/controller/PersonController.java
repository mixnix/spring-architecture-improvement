package com.example.demo.web.controller;

import com.example.demo.persistance.model.Person;
import com.example.demo.service.PersonService;
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
    private PersonService personService;

    private final Logger log = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/persons")
    public Collection<Person> getAllPersons() {
        log.info("GET /persons");
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable Long id) {
        log.info("GET /persons/{}", id);
        return personService.getPerson(id);
    }

    @PutMapping("/persons/{id}")
    public Person updatePerson(@ModelAttribute @Valid Person person, @PathVariable Long id){
        log.info("PUT /persons/{}, received data: {}", id, person);

        return personService.updatePerson(person, id);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable Long id){
        log.info("DELETE /persons/{}", id);

        personService.deletePerson(id);
    }
}

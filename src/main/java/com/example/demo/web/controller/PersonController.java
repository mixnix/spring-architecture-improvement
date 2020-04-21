package com.example.demo.web.controller;

import com.example.demo.persistance.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.web.dto.PersonDTO;
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
    public Collection<PersonDTO> getAllPersons() {
        log.info("GET /persons");
        return personService.getAllPersons();
    }

    @PostMapping("/persons")
    public PersonDTO createPerson(@ModelAttribute @Valid PersonDTO person) {
        log.info("POST /persons");
        return personService.createPerson(person);

    }

    @GetMapping("/persons/{id}")
    public PersonDTO getPerson(@PathVariable Long id) {
        log.info("GET /persons/{}", id);
        return personService.getPerson(id);
    }

    @PutMapping("/persons/{id}")
    public PersonDTO updatePerson(@ModelAttribute @Valid PersonDTO person, @PathVariable Long id){
        log.info("PUT /persons/{}, received data: {}", id, person);
        return personService.updatePerson(person, id);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable Long id){
        log.info("DELETE /persons/{}", id);
        personService.deletePerson(id);
    }
}

package com.example.demo.controller;

import com.example.demo.mapper.PersonMapper;
import com.example.demo.service.PersonService;
import com.example.demo.domain.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        log.info("GET /persons");
        return personMapper.mapPersonListToDTOList(personService.getAll());
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid PersonDTO person) {
        log.info("POST /persons");
        //todo: musze walidowac by nie dostac tu id, jak to zrobic by korzystac z jednej klasy (PersonDTO) ale miec rozne reguly walidacji
        return ResponseEntity.status(201)
                .body(personMapper.mapPersonToDTO(personService.create(personMapper.mapDTOToPerson(person))));
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Long id) {
        log.info("GET /persons/{}", id);
        return personMapper.mapPersonToDTO(personService.getById(id));
    }

    @PutMapping("/{id}")
    public PersonDTO updatePerson(@RequestBody @Valid PersonDTO person, @PathVariable Long id){
        log.info("PUT /persons/{}, received data: {}", id, person);
        return personMapper.mapPersonToDTO(personService.update(personMapper.mapDTOToPerson(person), id));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        log.info("DELETE /persons/{}", id);
        personService.deleteById(id);
    }
}

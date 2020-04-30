package com.example.demo.controller;

import com.example.demo.domain.dto.PersonDTO;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class PersonController {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        log.info("GET /api/persons");
        return personMapper.mapPersonListToDTOList(personService.getAll());
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid PersonDTO person) {
        log.info("POST /api/persons");
        //todo: musze walidowac by nie dostac tu id, jak to zrobic by korzystac z jednej klasy (PersonDTO) ale miec rozne reguly walidacji
            //todo: na razie robie to tak, ze gdy mapuje z dto do person to nie mapuje id, czy to poprawne rozwiazanie?
            //todo: a gdy mapuje z person na dto to juz mapuje id bo chce aby uzytkownik api wiedzial w jaki sposob dostac sie do resource-a
            //(jakiego id uzyc w zadaniu)
        return ResponseEntity.status(201)
                .body(personMapper.mapPersonToDTO(personService.create(personMapper.mapDTOToPerson(person))));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasPersonId(authentication,#id) or hasAuthority('ADMIN')")
    public PersonDTO getPerson(@PathVariable Long id) {
        log.info("GET /api/persons/{}", id);
        return personMapper.mapPersonToDTO(personService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasPersonId(authentication,#id) or hasAuthority('ADMIN')")
    public PersonDTO updatePerson(@RequestBody @Valid PersonDTO person, @PathVariable Long id){
        log.info("PUT /api/persons/{}, received data: {}", id, person);
        return personMapper.mapPersonToDTO(personService.update(personMapper.mapDTOToPerson(person), id));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        log.info("DELETE /api/persons/{}", id);
        personService.deleteById(id);
    }
}

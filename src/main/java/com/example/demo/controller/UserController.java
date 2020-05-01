package com.example.demo.controller;

import com.example.demo.domain.dto.*;
import com.example.demo.mapper.PersonalDataDTOMapper;
import com.example.demo.mapper.UserDTOMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    private final UserDTOMapper userDTOMapper;

    private final PersonalDataDTOMapper personalDataDTOMapper;

    @PostMapping(value = "/register")
    public ResponseEntity<RegistrationResponseDTO> createUser(@Valid @RequestBody RegistrationRequestDTO userDTO) {
        return ResponseEntity.status(201).body(
                userDTOMapper.mapUsertoRegistrationResponse(userService.create(userDTOMapper.mapDTOToUser(userDTO)))
        );
    }

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        log.info("GET /api/users");
        return personalDataDTOMapper.mapPersonListToDTOList(userService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasPersonId(authentication,#id) or hasRole('ADMIN')")
    public PersonDTO getPerson(@PathVariable Long id) {
        log.info("GET /api/users/{}", id);
        return personalDataDTOMapper.mapPersonToDTO(userService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasPersonId(authentication,#id) or hasRole('ADMIN')")
    public PersonDTO updatePerson(@RequestBody @Valid PersonDTO person, @PathVariable Long id){
        log.info("PUT /api/users/{}, received data: {}", id, person);
        return personalDataDTOMapper.mapPersonToDTO(userService.update(personalDataDTOMapper.mapDTOToPerson(person), id));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        log.info("DELETE /api/users/{}", id);
        userService.deleteById(id);
    }
}

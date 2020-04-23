package com.example.demo.mapper.impl;

import com.example.demo.domain.dao.Person;
import com.example.demo.domain.dto.PersonDTO;
import com.example.demo.mapper.PersonMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDTO mapPersonToDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .birthDate(person.getBirthDate())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .sex(person.getSex())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }

    @Override
    public Person mapDTOToPerson(PersonDTO personDTO) {
        return Person.builder()
                .birthDate(personDTO.getBirthDate())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .sex(personDTO.getSex())
                .phoneNumber(personDTO.getPhoneNumber())
                .build();
    }

    @Override
    public List<PersonDTO> mapPersonListToDTOList(List<Person> personList) {
        return personList.stream()
                .map(this::mapPersonToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> mapDTOListToPersonList(List<PersonDTO> personDTOList) {
        return personDTOList.stream()
                .map(this::mapDTOToPerson)
                .collect(Collectors.toList());
    }
}

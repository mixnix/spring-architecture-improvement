package com.example.demo.mapper;

import com.example.demo.domain.dao.Person;
import com.example.demo.domain.dto.PersonDTO;

import java.util.List;

public interface PersonMapper {
    PersonDTO mapPersonToDTO(Person person);
    Person mapDTOToPerson(PersonDTO personDTO);
    List<PersonDTO> mapPersonListToDTOList(List<Person> personList);
    List<Person> mapDTOListToPersonList(List<PersonDTO> personDTOList);
}

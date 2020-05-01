package com.example.demo.mapper.impl;

import com.example.demo.domain.dao.User;
import com.example.demo.domain.dto.PersonDTO;
import com.example.demo.mapper.PersonalDataDTOMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonalDataDTOMapperImpl implements PersonalDataDTOMapper {

    @Override
    public PersonDTO mapPersonToDTO(User person) {
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
    public User mapDTOToPerson(PersonDTO personDTO) {
        return User.builder()
                .birthDate(personDTO.getBirthDate())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .sex(personDTO.getSex())
                .phoneNumber(personDTO.getPhoneNumber())
                .build();
    }

    @Override
    public List<PersonDTO> mapPersonListToDTOList(List<User> personList) {
        return personList.stream()
                .map(this::mapPersonToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> mapDTOListToPersonList(List<PersonDTO> personDTOList) {
        return personDTOList.stream()
                .map(this::mapDTOToPerson)
                .collect(Collectors.toList());
    }
}

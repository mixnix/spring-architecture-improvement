package com.example.demo.mapper;

import com.example.demo.domain.dao.User;
import com.example.demo.domain.dto.PersonDTO;

import java.util.List;

public interface PersonalDataDTOMapper {
    PersonDTO mapPersonToDTO(User person);
    User mapDTOToPerson(PersonDTO personDTO);
    List<PersonDTO> mapPersonListToDTOList(List<User> personList);
    List<User> mapDTOListToPersonList(List<PersonDTO> personDTOList);
}

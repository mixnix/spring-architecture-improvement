package com.example.demo.service;

import com.example.demo.persistance.dao.PersonRepository;
import com.example.demo.persistance.model.Person;
import com.example.demo.web.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Collection<PersonDTO> getAllPersons(){
        Collection<Person> tempPersons = personRepository.findAll();
        Collection<PersonDTO> personsList = tempPersons.stream().map(
                PersonService::mapPersonToDTO
        ).collect(Collectors.toCollection(ArrayList::new));

        return personsList;
    }

    public PersonDTO getPerson(Long id){
        Optional<Person> personOptional = personRepository.findById(id);

        if(personOptional.isPresent()){
            return mapPersonToDTO(personOptional.get());
        }else{
            //todo: co zrobic w sytuacji gdy obiekt nie istnieje?
            return null;
        }
    }

    public PersonDTO updatePerson(PersonDTO person, Long id){
        Person tempPerson = personRepository.getOne(id);
        tempPerson.setFirstName(person.getFirstName());
        tempPerson.setLastName(person.getLastName());
        tempPerson.setSex(person.getSex());
        tempPerson.setBirtdate(person.getBirtdate());
        tempPerson.setPhoneNumber(person.getPhoneNumber());
        return mapPersonToDTO(personRepository.save(tempPerson));
    }

    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }

    private static PersonDTO mapPersonToDTO(Person s){
        return new PersonDTO(s.getFirstName(), s.getLastName(), s.getSex(), s.getBirtdate(), s.getPhoneNumber());
    }
}

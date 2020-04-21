package com.example.demo.service;

import com.example.demo.persistance.dao.PersonRepository;
import com.example.demo.persistance.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Collection<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Person getPerson(Long id){
        return personRepository.findById(id).get();
    }

    public Person updatePerson(Person person, Long id){
        Person tempPerson = personRepository.getOne(id);
        tempPerson.setFirstName(person.getFirstName());
        tempPerson.setLastName(person.getLastName());
        tempPerson.setSex(person.getSex());
        tempPerson.setBirtdate(person.getBirtdate());
        tempPerson.setPhoneNumber(person.getPhoneNumber());
        return personRepository.save(tempPerson);
    }

    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }

}

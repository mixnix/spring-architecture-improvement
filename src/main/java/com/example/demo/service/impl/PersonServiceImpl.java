package com.example.demo.service.impl;

import com.example.demo.repository.PersonRepository;
import com.example.demo.domain.dao.Person;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> getAll(){
    return personRepository.findAll();

    }

    @Override
    public Person create(Person person) {
        //don't allow id to be not null
        return personRepository.save(person);
    }

    @Override
    public Person getById(Long id){
        Optional<Person> personOptional = personRepository.findById(id);

        return personOptional
                .orElseThrow(() -> new EntityNotFoundException("Could not find person with id: " + id));
    }

    @Override
    public Person update(Person person, Long id){
        Person tempPerson = personRepository.getOne(id);

        tempPerson.setFirstName(person.getFirstName());
        tempPerson.setLastName(person.getLastName());
        tempPerson.setSex(person.getSex());
        tempPerson.setBirthDate(person.getBirthDate());
        tempPerson.setPhoneNumber(person.getPhoneNumber());

        return personRepository.save(tempPerson);
    }

    @Override
    public void deleteById(Long id){
        personRepository.deleteById(id);
    }
}

package com.example.demo.service.impl;

import com.example.demo.domain.dao.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //todo: dodaj walidacje uzytkownika!!!

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    @Override
    public List<User> getAll(){
        return userRepository.findAll();

    }

    @Override
    public User getById(Long id){
        Optional<User> personOptional = userRepository.findById(id);

        return personOptional
                .orElseThrow(() -> new EntityNotFoundException("Could not find person with id: " + id));
    }



    @Override
    public User update(User person, Long id){
        User tempPerson = userRepository.getOne(id);

        tempPerson.setFirstName(person.getFirstName());
        tempPerson.setLastName(person.getLastName());
        tempPerson.setSex(person.getSex());
        tempPerson.setPhoneNumber(person.getPhoneNumber());

        return userRepository.save(tempPerson);
    }

    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}

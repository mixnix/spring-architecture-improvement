package com.example.demo;

import com.example.demo.domain.dao.Authority;
import com.example.demo.domain.dao.Person;
import com.example.demo.domain.dao.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PersonRepository personRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... strings) throws Exception {
        Authority adminAuthority = authorityRepository.save(new Authority("ADMIN"));
        Authority employeeAuthority = authorityRepository.save(new Authority("EMPLOYEE"));

        Person agnieszkaPerson = personRepository.save(new Person("agnieszka", "nazwisko",
                "male", "123456789"));

        Person mlodyPerson = personRepository.save(new Person("mlody", "nazwiskomldego",
                "male", "123456789"));

        User agnieszka = new User();
        agnieszka.setUsername("manager@foo.com");
        agnieszka.setPassword(passwordEncoder.encode("foo"));
        agnieszka.setPerson(agnieszkaPerson);
        HashSet<Authority> agnieszkaAuthorities = new HashSet<>();
        agnieszkaAuthorities.add(adminAuthority);
        agnieszka.setAuthorities(agnieszkaAuthorities);
        userRepository.save(agnieszka);

        User mlody = new User();
        mlody.setUsername("mlody@foo.com");
        mlody.setPassword(passwordEncoder.encode("foo"));
        mlody.setPerson(mlodyPerson);
        HashSet<Authority> mlodyAuthorities = new HashSet<>();
        mlodyAuthorities.add(employeeAuthority);
        mlody.setAuthorities(mlodyAuthorities);
        userRepository.save(mlody);
    }
}

package com.example.demo;

import com.example.demo.domain.dao.Authority;
import com.example.demo.domain.dao.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... strings) throws Exception {
        Authority adminAuthority = authorityRepository.save(new Authority("ADMIN"));
        Authority employeeAuthority = authorityRepository.save(new Authority("EMPLOYEE"));

        User agnieszka = User.builder()
                .username("manager@foo.com")
                .password(passwordEncoder.encode("foo"))
                .firstName("agnieszka")
                .lastName("nazwisko")
                .sex("female")
                .phoneNumber("123456789")
                .build();

        HashSet<Authority> agnieszkaAuthorities = new HashSet<>();
        agnieszkaAuthorities.add(adminAuthority);
        agnieszka.setAuthorities(agnieszkaAuthorities);
        userRepository.save(agnieszka);

        User mlody = User.builder()
                .username("mlody@foo.com")
                .password(passwordEncoder.encode("foo"))
                .firstName("mlody")
                .lastName("nazwiskomldego")
                .sex("male")
                .phoneNumber("123456789")
                .build();
        HashSet<Authority> mlodyAuthorities = new HashSet<>();
        mlodyAuthorities.add(employeeAuthority);
        mlody.setAuthorities(mlodyAuthorities);
        userRepository.save(mlody);
    }
}

package com.example.demo.service.impl;

import com.example.demo.domain.dao.Person;
import com.example.demo.domain.dao.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PersonService;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PersonService personService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

    private final MyUserDetailsService userDetailsService;

    @Override
    public User create(User user, String firstName, String lastName) {
        Person person = personService.create(firstName, lastName);
        user.setPerson(person);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //todo: dodaj walidacje uzytkownika!!!

        return userRepository.save(user);
    }

    @Override
    public String authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}

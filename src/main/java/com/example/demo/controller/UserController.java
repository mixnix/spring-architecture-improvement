package com.example.demo.controller;

import com.example.demo.domain.dao.User;
import com.example.demo.domain.dto.AuthenticationRequestDTO;
import com.example.demo.domain.dto.AuthenticationResponseDTO;
import com.example.demo.domain.dto.RegistrationDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping(value = "/authenticate")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> createAuthenticationToken
            (@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
        log.info("POST /authenticate, authenthicating user:{}", authenticationRequestDTO);

        String jwt = userService.authenticate(authenticationRequestDTO.getUsername(),
                authenticationRequestDTO.getPassword());

        User user = userService.findByUsername(authenticationRequestDTO.getUsername());

        Long personId;
        if(user.getPerson() != null){
            personId = user.getPerson().getId();
        }else{
            personId =  null;
        }

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt, personId));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody RegistrationDTO userDTO) {
        return ResponseEntity.status(201).body(userService.create(
                userMapper.mapDTOToUser(userDTO), userDTO.getFirstName(), userDTO.getLastName()
                ));
    }
}

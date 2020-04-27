package com.example.demo.mapper.impl;

import com.example.demo.domain.dao.User;
import com.example.demo.domain.dto.RegistrationDTO;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapDTOToUser(RegistrationDTO registrationDTO) {
        return User.builder()
                .username(registrationDTO.getUsername())
                .password(registrationDTO.getPassword())
                .build();
    }
}

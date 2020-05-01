package com.example.demo.mapper.impl;

import com.example.demo.domain.dao.User;
import com.example.demo.domain.dto.RegistrationRequestDTO;
import com.example.demo.domain.dto.RegistrationResponseDTO;
import com.example.demo.mapper.UserDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapperImpl implements UserDTOMapper {

    @Override
    public User mapDTOToUser(RegistrationRequestDTO registrationRequestDTO) {
        return User.builder()
                .username(registrationRequestDTO.getUsername())
                .password(registrationRequestDTO.getPassword())
                .build();
    }

    @Override
    public RegistrationResponseDTO mapUsertoRegistrationResponse(User user) {
        return  RegistrationResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }
}

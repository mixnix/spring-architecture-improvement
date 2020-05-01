package com.example.demo.mapper;

import com.example.demo.domain.dao.User;
import com.example.demo.domain.dto.RegistrationRequestDTO;
import com.example.demo.domain.dto.RegistrationResponseDTO;

public interface UserDTOMapper {
    User mapDTOToUser(RegistrationRequestDTO registrationRequestDTO);
    RegistrationResponseDTO mapUsertoRegistrationResponse(User user);
}

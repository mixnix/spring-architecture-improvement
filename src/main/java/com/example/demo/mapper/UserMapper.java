package com.example.demo.mapper;

import com.example.demo.domain.dao.User;
import com.example.demo.domain.dto.RegistrationDTO;

public interface UserMapper {
    User mapDTOToUser(RegistrationDTO registrationDTO);
}

package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO implements Serializable {
    private final String jwt;

    private final Long personId;
}

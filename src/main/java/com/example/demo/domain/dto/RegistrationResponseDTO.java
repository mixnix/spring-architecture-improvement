package com.example.demo.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponseDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;
}

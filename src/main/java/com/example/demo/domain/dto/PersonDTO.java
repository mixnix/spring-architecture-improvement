package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String sex;

    private LocalDate birthDate;

    private String phoneNumber;
}

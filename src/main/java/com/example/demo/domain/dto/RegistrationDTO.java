package com.example.demo.domain.dto;

import com.example.demo.validator.EqualPasswords;
import com.example.demo.validator.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@EqualPasswords
@UniqueUsername
public class RegistrationDTO {

    @Email(message = "Wprowadź poprawny email")
    @NotBlank(message = "Wprowadź email")
    private String username;

    @Size(min = 6, message = "Hasło musi mieć conajmniej 6 znaków")
    private String password;

    private String confirmPassword;

    private String firstName;

    private String lastName;
}

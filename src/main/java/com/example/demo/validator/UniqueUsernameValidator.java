package com.example.demo.validator;

import com.example.demo.domain.dto.RegistrationRequestDTO;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, RegistrationRequestDTO> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraint) {
    }
 
    @Override
    public boolean isValid(RegistrationRequestDTO form, ConstraintValidatorContext context) {
        return !userRepository.findByUsername(form.getUsername()).isPresent();
    }
 
}
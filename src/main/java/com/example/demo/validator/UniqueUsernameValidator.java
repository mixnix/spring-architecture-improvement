package com.example.demo.validator;

import com.example.demo.domain.dto.RegistrationDTO;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, RegistrationDTO> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraint) {
    }
 
    @Override
    public boolean isValid(RegistrationDTO form, ConstraintValidatorContext context) {
        return userRepository.findByUsername(form.getUsername()) == null;
    }
 
}
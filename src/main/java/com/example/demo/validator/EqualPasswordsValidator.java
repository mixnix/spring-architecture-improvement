package com.example.demo.validator;


import com.example.demo.domain.dto.RegistrationRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, RegistrationRequestDTO> {
 
    @Override
    public void initialize(EqualPasswords constraint) {
    }
 
    @Override
    public boolean isValid(RegistrationRequestDTO form, ConstraintValidatorContext context) {
        return form.getPassword().equals(form.getConfirmPassword());
    }
 
}
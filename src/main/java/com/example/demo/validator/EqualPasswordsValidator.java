package com.example.demo.validator;


import com.example.demo.domain.dto.RegistrationDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, RegistrationDTO> {
 
    @Override
    public void initialize(EqualPasswords constraint) {
    }
 
    @Override
    public boolean isValid(RegistrationDTO form, ConstraintValidatorContext context) {
        return form.getPassword().equals(form.getConfirmPassword());
    }
 
}
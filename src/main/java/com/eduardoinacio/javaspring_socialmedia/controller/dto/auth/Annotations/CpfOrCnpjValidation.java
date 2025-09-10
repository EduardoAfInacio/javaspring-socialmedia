package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CpfOrCnpjValidation implements ConstraintValidator<ValidCpfOrCnpj, String> {
    @Override
    public boolean isValid(String cpfOrCnpj, ConstraintValidatorContext constraintValidatorContext) {
        if(cpfOrCnpj == null || cpfOrCnpj.isBlank()){
            return true;
        }

        cpfOrCnpj = cpfOrCnpj.replaceAll("[^\\d]", "");

        if(cpfOrCnpj.length() == 11){
            boolean valid = new CPFValidator().isValid(cpfOrCnpj, constraintValidatorContext);
            if(!valid){
                buildCustomMessage(constraintValidatorContext, "Invalid CPF");
            }
            return valid;
        }

        else if(cpfOrCnpj.length() == 14){
            boolean valid = new CNPJValidator().isValid(cpfOrCnpj, constraintValidatorContext);
            if(!valid){
                buildCustomMessage(constraintValidatorContext, "Invalid CNPJ");
            }
            return valid;
        }

        else{
            buildCustomMessage(constraintValidatorContext, "Document must have 11(CPF) or 14(CNPJ) characters");
            return false;
        }
    }

    private void buildCustomMessage(ConstraintValidatorContext constraintValidatorContext, String message){
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}

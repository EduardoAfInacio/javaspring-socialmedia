package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidation implements ConstraintValidator<ValidCpfOrCnpj, String> {

    @Override
    public boolean isValid(String cpfOrCnpj, ConstraintValidatorContext context) {
        if (cpfOrCnpj == null || cpfOrCnpj.isBlank()) {
            return true;
        }

        cpfOrCnpj = cpfOrCnpj.replaceAll("[^\\d]", "");

        if (cpfOrCnpj.length() == 11) {
            if (!isValidCPF(cpfOrCnpj)) {
                buildCustomMessage(context, "Invalid CPF");
                return false;
            }
        } else if (cpfOrCnpj.length() == 14) {
            if (!isValidCNPJ(cpfOrCnpj)) {
                buildCustomMessage(context, "Invalid CNPJ");
                return false;
            }
        } else {
            buildCustomMessage(context, "Document must have 11(CPF) or 14(CNPJ) characters");
            return false;
        }

        return true;
    }

    private void buildCustomMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

    private boolean isValidCPF(String cpf) {
        if (cpf.chars().distinct().count() == 1) return false;

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) firstDigit = 0;
        if ((cpf.charAt(9) - '0') != firstDigit) return false;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) secondDigit = 0;

        return (cpf.charAt(10) - '0') == secondDigit;
    }

    private boolean isValidCNPJ(String cnpj) {
        if (cnpj.chars().distinct().count() == 1) return false;

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weights1[i];
        }
        int firstDigit = sum % 11;
        firstDigit = firstDigit < 2 ? 0 : 11 - firstDigit;
        if ((cnpj.charAt(12) - '0') != firstDigit) return false;

        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weights2[i];
        }
        int secondDigit = sum % 11;
        secondDigit = secondDigit < 2 ? 0 : 11 - secondDigit;

        return (cnpj.charAt(13) - '0') == secondDigit;
    }
}

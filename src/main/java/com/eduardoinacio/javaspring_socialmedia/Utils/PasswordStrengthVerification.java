package com.eduardoinacio.javaspring_socialmedia.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordStrengthVerification {
    public static List<String> verifyPasswordStrength(String password){
        List<String> errors = new ArrayList<>();
        passwordValidateSize(password, errors);
        passwordValidateUpperCase(password, errors);
        passwordValidateLowerCase(password, errors);
        passwordValidateDigits(password, errors);
        passwordValidateSpecialCharacter(password, errors);
        return errors;
    }

    private static void passwordValidateSize(String password, List<String> errors){
        if(password.length() < 8){
            errors.add("Password must be at least 8 characters long");
        }
    }

    private static void passwordValidateUpperCase(String password, List<String> errors){
        if(!Pattern.matches(".*[A-Z].*", password)){
            errors.add("Password must contain at least one uppercase letter");
        }
    }

    private static void passwordValidateLowerCase(String password, List<String> errors){
        if(!Pattern.matches(".*[a-z].*", password)){
            errors.add("Password must contain at least one lowercase letter");
        }
    }

    private static void passwordValidateDigits(String password, List<String> errors){
        if(!Pattern.matches(".*[0-9].*", password)){
            errors.add("Password must contain at least one numeric digit");
        }
    }

    private static void passwordValidateSpecialCharacter(String password, List<String> errors){
        if(!Pattern.matches(".*[\\W].*", password)){
            errors.add("Password must contain at least one special character");
        }
    }


}

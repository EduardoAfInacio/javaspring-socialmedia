package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations.StrongPassword;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations.ValidCpfOrCnpj;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations.ValidDateOfBirth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UserRegisterRequest(@NotBlank @ValidCpfOrCnpj String document,
                                  @NotBlank String name,
                                  @NotBlank @Email String email,
                                  @NotNull @Past @ValidDateOfBirth(age = 18) LocalDate birthDate,
                                  @NotBlank @StrongPassword String password) {
}

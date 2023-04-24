package com.academy.nhnmartcs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Getter
public class User {
    @NotBlank
    String id;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]){5,20}$")
    String password;

    @NotBlank
    String name;

    @NotNull
    Authorization authorization;

}

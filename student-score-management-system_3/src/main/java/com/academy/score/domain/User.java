package com.academy.score.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;

@Value
@AllArgsConstructor
public class User {
    @Email
    String email;
    String password;
}

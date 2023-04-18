package com.academy.score.domain;

import lombok.Value;

import javax.validation.constraints.*;

@Value
public class StudentRegisterRequest {
    @NotBlank
    String name;

    @Email
    String email;

    @Min(0)
    @Max(100)
    int score;

    @NotBlank
    @Size(min = 0, max = 200)
    String comment;
}

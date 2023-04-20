package com.academy.score.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
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





package com.academy.score.domain;

import lombok.Value;

@Value
public class Student {
    long id;
    String name;
    String email;
    int score;
    String comment;

    public Student(long id, String name, String email, int score, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

}

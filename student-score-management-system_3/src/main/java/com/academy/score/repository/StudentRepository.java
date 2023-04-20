package com.academy.score.repository;

import com.academy.score.domain.Student;

public interface StudentRepository {
    Student register(String name, String email, int score, String comment);

    Student modify(long id, String name, String email, int score, String comment);

    Student getStudent(long id);

    boolean exists(long id);
}

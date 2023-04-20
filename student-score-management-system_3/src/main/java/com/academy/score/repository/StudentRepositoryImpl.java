package com.academy.score.repository;

import com.academy.score.domain.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {
    Map<Long, Student> studentMap = new HashMap<>();
    static long id = 0;

    @Override
    public Student register(String name, String email, int score, String comment) {
        ++id;
        studentMap.put(id, new Student(id, name, email, score, comment));
        return studentMap.get(id);
    }

    @Override
    public Student modify(long id, String name, String email, int score, String comment) {
        studentMap.put(id, new Student(id, name, email, score, comment));
        return studentMap.get(id);
    }

    @Override
    public Student getStudent(long id) {
        return studentMap.get(id);
    }

    @Override
    public boolean exists(long id) {
        return studentMap.containsKey(id);
    }
}

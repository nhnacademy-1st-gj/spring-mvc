package com.academy.score.repository;

import com.academy.score.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    Map<String, User> userMap = new HashMap<>();

    @Override
    public User register(String email, String password) {
        userMap.put(email, new User(email, password));
        return userMap.get(email);
    }

    @Override
    public User modify(String email, String password) {
        userMap.put(email, new User(email, password));
        return userMap.get(email);
    }

    @Override
    public User getUser(String email) {
        return userMap.get(email);
    }

    @Override
    public boolean exists(String email) {
        return userMap.containsKey(email);
    }
}

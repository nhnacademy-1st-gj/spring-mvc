package com.academy.nhnmartcs.repository;

import com.academy.nhnmartcs.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    Map<String, User> userMap = new HashMap<>();

    public Map<String, User> getUserMap() {
        return userMap;
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public boolean exist(String id) {
        return userMap.containsKey(id);
    }
}

package com.academy.score.repository;

import com.academy.score.domain.User;

public interface UserRepository {
    User register(String email, String password);

    User modify(String email, String password);

    User getUser(String email);

    boolean exists(String email);
}

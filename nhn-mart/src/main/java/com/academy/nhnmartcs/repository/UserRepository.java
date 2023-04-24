package com.academy.nhnmartcs.repository;

import com.academy.nhnmartcs.domain.User;

import java.util.Map;

public interface UserRepository {

    User getUser(String id);

    boolean exist(String id);

    Map<String, User> getUserMap();


}

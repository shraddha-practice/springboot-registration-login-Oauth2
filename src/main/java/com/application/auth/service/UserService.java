package com.application.auth.service;

import com.application.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

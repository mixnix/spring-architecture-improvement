package com.example.demo.service;

import com.example.demo.domain.dao.User;

public interface UserService {
    User create(User user, String firstName, String lastName);
    String authenticate(String username, String password) throws Exception;
    User findByUsername(String username);
}
